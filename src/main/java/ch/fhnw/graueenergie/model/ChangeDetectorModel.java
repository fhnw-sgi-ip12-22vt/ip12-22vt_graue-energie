package ch.fhnw.graueenergie.model;

import ch.fhnw.graueenergie.entity.EnergyActor;
import ch.fhnw.graueenergie.entity.EnergyBoardPosition;
import ch.fhnw.graueenergie.entity.EnergyEntity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangeDetectorModel {

  private EnergyEntity[] lastChanges;

  private final Map<EnergyBoardPosition, EnergyEntity> boardState;

  public ChangeDetectorModel(EnergyEntity[] lastState) {
    boardState = new HashMap<>();
    stateChanged(lastState);
  }

  public boolean stateChanged(EnergyEntity[] newState) {
    List<EnergyEntity> changes = new ArrayList<>();
    for (EnergyEntity energyEntity : newState) {
      if (boardState.get(energyEntity.getBoardPosition()) == null ||
          !boardState.get(energyEntity.getBoardPosition()).equals(energyEntity)) {
        changes.add(energyEntity);
        boardState.put(energyEntity.getBoardPosition(), energyEntity);
      }
    }

    this.lastChanges = changes.toArray(EnergyEntity[]::new);
      return
        changes.size() > 0;
    }

    public EnergyEntity[] getBoardState() {
    return boardState.values().stream()
        .sorted(Comparator.comparingInt(e -> e.getBoardPosition().positionId))
        .toArray(EnergyEntity[]::new);
  }

  public EnergyActor getLastChange() {
    if (lastChanges.length == 0) {
      return null;
    }

    return lastChanges[0].getEnergyActor();
  }
}
