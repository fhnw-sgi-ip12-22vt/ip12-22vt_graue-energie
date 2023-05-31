package ch.fhnw.graueenergie.model;

import ch.fhnw.graueenergie.entity.DisplayState;
import ch.fhnw.graueenergie.entity.EnergyBoardPosition;
import ch.fhnw.graueenergie.entity.EnergyConsumer;
import ch.fhnw.graueenergie.entity.EnergyEntity;
import ch.fhnw.graueenergie.entity.EnergyProducer;
import ch.fhnw.graueenergie.view.View;
import java.util.Arrays;

public final class EnergyCalculatorModel implements Model {

  private final View view;
  private final ChangeDetectorModel changeDetectorModel;
  private final QuizModel quizModel;
  private final InstructionTextModel instructionTextModel;

  public EnergyCalculatorModel(View view) {
    this.view = view;

    EnergyEntity[] energyEntities = initializeEmptyProducerForEachPosition();
    changeDetectorModel = new ChangeDetectorModel(energyEntities);
    quizModel = new QuizModel();
    instructionTextModel = new InstructionTextModel();
  }

  private EnergyEntity[] initializeEmptyProducerForEachPosition() {
    EnergyEntity[] energyEntities = new EnergyEntity[EnergyBoardPosition.values().length];
    for (int i = 0; i < energyEntities.length; i++) {
      energyEntities[i] = new EnergyEntity(
          EnergyBoardPosition.values()[i],
          EnergyProducer.NO_ENERGY_PRODUCER.id);
    }
    return energyEntities;
  }

  public void changeBoardState(EnergyEntity[] changes) {
    if (!changeDetectorModel.stateChanged(changes)) {
      return;
    }

    EnergyEntity[] energyEntities = changeDetectorModel.getBoardState();

    System.out.println("---New Change---");

    for (int i = 0; i < energyEntities.length; i++) {
      if (i == 0) {
        System.out.println(i + ": " + energyEntities[i].getConsumer().name());
      } else {
        System.out.println(i + ": " + energyEntities[i].getProducer().name());
      }
    }

    EnergyConsumer consumer = energyEntities[EnergyBoardPosition.POS0.positionId].getConsumer();

    double energyBarPercent = 0;
    double ubpBarPercent = 0;

    if (consumer.id != EnergyConsumer.NO_CONSUMER.id) {
      energyBarPercent = getCalculatedEnergyBarInPercent(consumer, energyEntities);
      ubpBarPercent = getCalculatedUbpBarInPercent(consumer, energyEntities);
    }

    System.out.println("---End Change---");

    DisplayState displayState = new DisplayState(
        instructionTextModel.getInstructionText(energyBarPercent, ubpBarPercent, consumer),
        quizModel.getQuestion(changeDetectorModel.getLastChange()),
        consumer,
        energyBarPercent,
        ubpBarPercent
    );

    view.updateDisplay(displayState);
  }

  private double getCalculatedEnergyBarInPercent(EnergyConsumer consumer,
      EnergyEntity[] energyEntities) {
    return Arrays.stream(energyEntities).mapToDouble(EnergyEntity::getEnergyProduced).sum()
        / consumer.energyNeeded;
  }

  private double getCalculatedUbpBarInPercent(EnergyConsumer consumer,
      EnergyEntity[] energyEntities) {
    double increaseNumberToHundertPercentFactor = 10.0 / 6.0;

    return Arrays.stream(energyEntities)
        .mapToDouble(EnergyEntity::getUbpPerEnergyProduced).sum()
        / (consumer.maxUbp * increaseNumberToHundertPercentFactor);
  }
}
