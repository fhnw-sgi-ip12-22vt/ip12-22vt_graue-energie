package ch.fhnw.graueenergie.entity;

import ch.fhnw.graueenergie.exception.PositionNotFoundException;

public enum EnergyBoardPosition {
  POS0(0, 4, 1.0, BoardPositionType.CONSUMER),
  POS1(1, 9, 0.90, BoardPositionType.ALTITUDE_NEEDED),
  POS2(2, 5, 0.86, BoardPositionType.OTHER),
  POS3(3, 2, 0.92, BoardPositionType.WATER_NEEDED),
  POS4(4, 3, 0.96, BoardPositionType.WATER_NEEDED),
  POS5(5, 8, 0.82, BoardPositionType.ALTITUDE_NEEDED),
  POS6(6, 6, 0.74, BoardPositionType.OTHER),
  POS7(7, 1, 0.80, BoardPositionType.WATER_NEEDED),
  POS8(8, 3, 0.84, BoardPositionType.OTHER),
  POS9(9, 0, 0.80, BoardPositionType.ALTITUDE_NEEDED),
  POS10(10, 7, 0.70, BoardPositionType.OTHER);

  public final int positionId;
  public final int scannerId;
  public final double energyDeclineFromDistance;
  public final BoardPositionType type;

  EnergyBoardPosition(int positionId, int scannerId, double energyDeclineFromDistance, BoardPositionType type) {
    this.positionId = positionId;
    this.scannerId = scannerId;
    this.energyDeclineFromDistance = energyDeclineFromDistance;
    this.type = type;
  }

  public static EnergyBoardPosition valueOf(int scannerId) {
    for (int i = 0; i < EnergyBoardPosition.values().length; i++) {
      if (EnergyBoardPosition.values()[i].scannerId == scannerId) {
        return EnergyBoardPosition.values()[i];
      }
    }

    throw new PositionNotFoundException();
  }

  public boolean isEnergyActorAllowedOnType(int energyActorId) {
    return type.isEnergyActorAllowed(energyActorId);
  }
}
