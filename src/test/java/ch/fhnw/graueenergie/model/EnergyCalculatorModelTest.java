package ch.fhnw.graueenergie.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ch.fhnw.graueenergie.entity.EnergyBoardPosition;
import ch.fhnw.graueenergie.entity.EnergyConsumer;
import ch.fhnw.graueenergie.entity.EnergyEntity;
import ch.fhnw.graueenergie.entity.EnergyProducer;
import ch.fhnw.graueenergie.view.MockView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class EnergyCalculatorModelTest {

  private final EnergyConsumer consumerCity = EnergyConsumer.CITY;
  private final EnergyConsumer consumerVillage = EnergyConsumer.VILLAGE;
  private final EnergyBoardPosition position = EnergyBoardPosition.POS1;
  private final int energyProducerWindPowerPlantId = 4;
  private final int energyProducerSolarPanelsId = 5;
  private Model model;
  private MockView view;

  private static EnergyEntity[] createScannerEntities(EnergyConsumer consumer, String position,
      int producerId) {
    return new EnergyEntity[]{
        new EnergyEntity(EnergyBoardPosition.POS0, consumer.id),
        new EnergyEntity(EnergyBoardPosition.valueOf(Integer.valueOf(position)), producerId)
    };
  }

  private static double getCalculatedEnergyProducedValue(EnergyProducer producer,
      EnergyBoardPosition position,
      EnergyConsumer consumer) {
    return (producer.energyProduced * position.energyDeclineFromDistance)
        / consumer.energyNeeded;
  }

  private static double getCalculatedUbpPerEnergyProducedValue(EnergyProducer producer,
      EnergyConsumer consumer) {
    final double increaseNumberToHundertPercentFactor = 10.0 / 6.0;

    return (double) producer.energyProduced * producer.ubpPerEnergyProduced
        / (consumer.maxUbp * increaseNumberToHundertPercentFactor);
  }

  @BeforeEach
  void init() {
    view = new MockView();
    model = new EnergyCalculatorModel(view);
  }

  @Test
  void createModelTest() {
    assertNotNull(model);
  }

  @Test
  void changeBoardStateForInstructionTextWithConsumerTest() {
    model.changeBoardState(new EnergyEntity[]{new EnergyEntity(EnergyBoardPosition.POS0, 10)});

    assertEquals("Bitte wählen Sie noch einen Produzenten.", view.displayState.instructionText());
  }

  @Test
  void changeBoardStateForUbpBarProgressWithEnergyProducerAndCityConsumerTest() {
    model.changeBoardState(
        createScannerEntities(consumerCity, String.valueOf(position.scannerId),
            energyProducerWindPowerPlantId));

    assertEquals(
        getCalculatedUbpPerEnergyProducedValue(EnergyProducer.WIND_POWER_PLANT, consumerCity),
        view.displayState.ubpBarProgress()
    );
  }

  @Test
  void changeBoardStateForEnergyProgressBarWithEnergyProducerAndCityConsumerTest() {
    model.changeBoardState(
        createScannerEntities(consumerCity, String.valueOf(position.scannerId),
            energyProducerWindPowerPlantId));

    assertEquals(
        getCalculatedEnergyProducedValue(EnergyProducer.WIND_POWER_PLANT, position, consumerCity),
        view.displayState.energyBarProgress()
    );
  }

  @Test
  void changeBoardStateForUbpBarProgressWithEnergyProducerAndVillageConsumerTest() {
    model.changeBoardState(
        createScannerEntities(consumerVillage, String.valueOf(position.scannerId),
            energyProducerWindPowerPlantId));

    assertEquals(getCalculatedUbpPerEnergyProducedValue(EnergyProducer.WIND_POWER_PLANT,
            consumerVillage),
        view.displayState.ubpBarProgress()
    );
  }

  @Test
  void changeBoardStateForEnergyProgressBarWithEnergyProducerAndVillageConsumerTest() {
    model.changeBoardState(
        createScannerEntities(consumerVillage, String.valueOf(position.scannerId),
            energyProducerWindPowerPlantId));

    assertEquals(
        getCalculatedEnergyProducedValue(EnergyProducer.WIND_POWER_PLANT, position,
            consumerVillage),
        view.displayState.energyBarProgress()
    );
  }

  @Nested
  class ModelWithInitialBoardState {

    @BeforeEach
    public void initializeWithBoardState() {
      model.changeBoardState(
          createScannerEntities(consumerCity, String.valueOf(position.scannerId),
              energyProducerSolarPanelsId));
    }

    @Test
    void changeBoardStateToLatestStateUbpProducedTest() {
      model.changeBoardState(
          createScannerEntities(consumerCity, String.valueOf(position.scannerId),
              energyProducerWindPowerPlantId));

      assertEquals(getCalculatedUbpPerEnergyProducedValue(EnergyProducer.WIND_POWER_PLANT,
              consumerCity),
          view.displayState.ubpBarProgress()
      );
    }

    @Test
    void changeBoardStateToLatestStateEnergyProducedTest() {
      model.changeBoardState(
          createScannerEntities(consumerCity, String.valueOf(position.scannerId),
              energyProducerWindPowerPlantId));

      assertEquals(
          getCalculatedEnergyProducedValue(EnergyProducer.WIND_POWER_PLANT, position, consumerCity),
          view.displayState.energyBarProgress()
      );
    }
  }
}
