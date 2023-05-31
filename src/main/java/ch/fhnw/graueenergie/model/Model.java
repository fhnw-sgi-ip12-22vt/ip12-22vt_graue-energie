package ch.fhnw.graueenergie.model;

import ch.fhnw.graueenergie.entity.EnergyEntity;

public interface Model {

    void changeBoardState(EnergyEntity[] scannerEntity);
}
