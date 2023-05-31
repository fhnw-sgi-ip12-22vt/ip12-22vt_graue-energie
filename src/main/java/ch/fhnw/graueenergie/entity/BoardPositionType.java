package ch.fhnw.graueenergie.entity;

public enum BoardPositionType {
  CONSUMER(
      new int[]{EnergyConsumer.NO_CONSUMER.id, EnergyConsumer.CITY.id, EnergyConsumer.VILLAGE.id,
          EnergyConsumer.INDUSTRIAL_AREA.id}),
  WATER_NEEDED(new int[]{EnergyProducer.NO_ENERGY_PRODUCER.id, EnergyProducer.NUCLEAR_POWER_PLANT.id,
      EnergyProducer.HYDROELECTRIC_POWER_STATION.id}),
  ALTITUDE_NEEDED(new int[]{EnergyProducer.NO_ENERGY_PRODUCER.id, EnergyProducer.WIND_POWER_PLANT.id,
      EnergyProducer.SOLAR_PANELS.id}),
  OTHER(new int[]{EnergyProducer.NO_ENERGY_PRODUCER.id, EnergyProducer.COAL_FIRED_POWER_STATION.id});

  public final int[] allowedEnergyActorIds;

  BoardPositionType(int[] allowedEnergyActorIds) {
    this.allowedEnergyActorIds = allowedEnergyActorIds;
  }

  public boolean isEnergyActorAllowed(int id) {
    for (int allowedEnergyActorId : allowedEnergyActorIds) {
      if (allowedEnergyActorId == id) {
        return true;
      }
    }

    return false;
  }
}
