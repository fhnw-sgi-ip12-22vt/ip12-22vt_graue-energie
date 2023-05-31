package ch.fhnw.graueenergie.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EnergyProducerTest {

  @Test
  void valueOfTest() {
    for (int i = 0; i < EnergyProducer.values().length; i++) {
      assertEquals(EnergyProducer.values()[i],
          EnergyProducer.valueOf(EnergyProducer.values()[i].id));
    }
  }

  @Test
  void valueOfNonExistingValueTest() {
    int nonExistingEnergyBoardPosition = 999999;

    assertEquals(EnergyProducer.NO_ENERGY_PRODUCER,
        EnergyProducer.valueOf(nonExistingEnergyBoardPosition));
  }
}
