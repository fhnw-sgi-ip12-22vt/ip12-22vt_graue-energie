package ch.fhnw.graueenergie.entity;

public enum EnergyProducer implements EnergyActor {
  NO_ENERGY_PRODUCER(0, 0, 0),
  NUCLEAR_POWER_PLANT(1, 870, 466),
  COAL_FIRED_POWER_STATION(2, 578, 272),
  HYDROELECTRIC_POWER_STATION(3, 500, 24),
  WIND_POWER_PLANT(4, 400, 76),
  SOLAR_PANELS(5, 70, 164);

  public final int id;
  public final int energyProduced;
  public final int ubpPerEnergyProduced;

  EnergyProducer(int id, int energyProduced, int ubpPerEnergyProduced) {
    this.id = id;
    this.energyProduced = energyProduced;
    this.ubpPerEnergyProduced = ubpPerEnergyProduced;
  }

  public static EnergyProducer valueOf(int producerId) {
    for (int i = 0; i < EnergyProducer.values().length; i++) {
      if (EnergyProducer.values()[i].id == producerId) {
        return EnergyProducer.values()[i];
      }
    }

    return EnergyProducer.NO_ENERGY_PRODUCER;
  }

  @Override
  public int getId() {
    return id;
  }
}
