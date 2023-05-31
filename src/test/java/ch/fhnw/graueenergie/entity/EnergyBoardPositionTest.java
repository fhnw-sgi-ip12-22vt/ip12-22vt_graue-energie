package ch.fhnw.graueenergie.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ch.fhnw.graueenergie.exception.PositionNotFoundException;
import org.junit.jupiter.api.Test;

class EnergyBoardPositionTest {

  @Test
  void valueOfTest() {
    for (int i = 0; i < EnergyBoardPosition.values().length; i++) {
      assertEquals(EnergyBoardPosition.values()[i],
          EnergyBoardPosition.valueOf(EnergyBoardPosition.values()[i].positionId));
    }
  }

  @Test
  void valueOfNonExistingValueTest() {
    int nonExistingEnergyBoardPosition = 999999;
    Exception exception = assertThrows(PositionNotFoundException.class,
        () -> EnergyBoardPosition.valueOf(nonExistingEnergyBoardPosition));

    String expectedMessage = "Position not found Exception!";
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }
}
