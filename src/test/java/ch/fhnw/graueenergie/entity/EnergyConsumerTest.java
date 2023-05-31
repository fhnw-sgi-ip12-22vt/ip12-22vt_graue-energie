package ch.fhnw.graueenergie.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EnergyConsumerTest {

  @Test
  void valueOfTest() {
    for (int i = 0; i < EnergyConsumer.values().length; i++) {
      assertEquals(EnergyConsumer.values()[i],
          EnergyConsumer.valueOf(EnergyConsumer.values()[i].id));
    }
  }

  @Test
  void valueOfNonExistingValueTest() {
    int nonExistingEnergyBoardPosition = 999999;

    assertEquals(EnergyConsumer.NO_CONSUMER,
        EnergyConsumer.valueOf(nonExistingEnergyBoardPosition));
  }
}
