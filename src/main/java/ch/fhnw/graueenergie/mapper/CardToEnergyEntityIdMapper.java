package ch.fhnw.graueenergie.mapper;

import ch.fhnw.graueenergie.entity.EnergyConsumer;
import ch.fhnw.graueenergie.entity.EnergyProducer;

public final class CardToEnergyEntityIdMapper {

  private CardToEnergyEntityIdMapper() {
  }

  public static Integer map(String value) {
    return switch (value) {
      case "550BCECF", "A7DD51FA" -> EnergyProducer.NUCLEAR_POWER_PLANT.id;
      case "E941D2CF", "EA84CDCF", "27504BFA" -> EnergyProducer.WIND_POWER_PLANT.id;
      case "8479CDCF", "C3D4D31C", "D7324BFA", "47B451FA" ->
          EnergyProducer.HYDROELECTRIC_POWER_STATION.id;
      case "11D9D2CF", "B32E4FA6", "2244B5EC" -> EnergyProducer.SOLAR_PANELS.id;
      case "C0C1CDCF", "172DC6B4", "C7A74CFA" -> EnergyProducer.COAL_FIRED_POWER_STATION.id;
      case "CC20D2CF", "729CD1EC" -> EnergyConsumer.INDUSTRIAL_AREA.id;
      case "978199B5", "C7764CFA" -> EnergyConsumer.CITY.id;
      case "576DBDB5", "37DD3CFA" -> EnergyConsumer.VILLAGE.id;
      default -> EnergyProducer.NO_ENERGY_PRODUCER.id;
    };
  }
}
