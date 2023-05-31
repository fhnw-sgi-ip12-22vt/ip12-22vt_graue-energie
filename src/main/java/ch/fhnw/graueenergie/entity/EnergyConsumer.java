package ch.fhnw.graueenergie.entity;

public enum EnergyConsumer implements EnergyActor {
  NO_CONSUMER(0, 0, 0),
  CITY(10, 800, 200_000),
  VILLAGE(11, 150, 40_000),
  INDUSTRIAL_AREA(12, 1_500, 1_000_000);

  public final int id;
  public final int energyNeeded;
  public final int maxUbp;
  public static final EnergyBoardPosition position = EnergyBoardPosition.POS0;

  EnergyConsumer(int id, int energyNeeded, int maxUbp) {
    this.id = id;
    this.energyNeeded = energyNeeded;
    this.maxUbp = maxUbp;
  }

  public static EnergyConsumer valueOf(int id) {
    for (int i = 0; i < EnergyConsumer.values().length; i++) {
      if (EnergyConsumer.values()[i].id == id) {
        return EnergyConsumer.values()[i];
      }
    }

    return EnergyConsumer.NO_CONSUMER;
  }

  @Override
  public int getId() {
    return id;
  }
}
