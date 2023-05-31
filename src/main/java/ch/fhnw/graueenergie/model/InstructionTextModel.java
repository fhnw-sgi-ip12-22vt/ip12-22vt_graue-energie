package ch.fhnw.graueenergie.model;

import ch.fhnw.graueenergie.entity.EnergyConsumer;
import ch.fhnw.graueenergie.entity.InstructionTextsEntity;
import ch.fhnw.graueenergie.helper.JsonHelper;

public final class InstructionTextModel {

  private final InstructionTextsEntity instructionTexts;

  InstructionTextModel() {
    instructionTexts = JsonHelper.read("/json/instructionText.json", InstructionTextsEntity.class);
  }

  public String getInstructionText(double energyBarState,
      double ubpBarState, EnergyConsumer consumer) {

    if (consumer != null && consumer.id == EnergyConsumer.NO_CONSUMER.id) {
      return instructionTexts.chooseConsumer();
    }

    if (energyBarState < 1.0) {
      return instructionTexts.chooseProducer();
    }

    if (energyBarState >= 1.0 && ubpBarState > 0.6) {
      return instructionTexts.ubpTooHigh();
    }

    return instructionTexts.congratulations1() + getConsumerName(consumer.id)
        + instructionTexts.congratulations2();
  }

  private String getConsumerName(int consumerId) {
    if (consumerId == EnergyConsumer.CITY.id) {
      return instructionTexts.city();
    } else if (consumerId == EnergyConsumer.VILLAGE.id) {
      return instructionTexts.village();
    }

    return instructionTexts.industrialArea();
  }
}
