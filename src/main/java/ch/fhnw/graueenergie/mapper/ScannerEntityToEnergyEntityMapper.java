package ch.fhnw.graueenergie.mapper;

import ch.fhnw.graueenergie.entity.EnergyBoardPosition;
import ch.fhnw.graueenergie.entity.EnergyEntity;
import ch.fhnw.graueenergie.entity.ScannerEntity;
import java.util.Arrays;
import java.util.Objects;

public final class ScannerEntityToEnergyEntityMapper {

  private ScannerEntityToEnergyEntityMapper() {
  }

  public static EnergyEntity map(ScannerEntity scannerEntity) {
    return new EnergyEntity(
        EnergyBoardPosition.valueOf(scannerEntity.getId()),
        Integer.parseInt(scannerEntity.getValue())
    );
  }

  public static EnergyEntity[] map(ScannerEntity[] scannerEntities) {
    return Arrays.stream(scannerEntities)
        .filter(Objects::nonNull)
        .map(ScannerEntityToEnergyEntityMapper::map)
        .toArray(EnergyEntity[]::new);
  }
}
