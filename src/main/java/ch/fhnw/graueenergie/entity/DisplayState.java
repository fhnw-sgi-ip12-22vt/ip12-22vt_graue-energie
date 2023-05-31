package ch.fhnw.graueenergie.entity;

public record DisplayState(String instructionText,
                           QuestionEntity questionEntity,
                           EnergyConsumer energyConsumer,
                           double energyBarProgress,
                           double ubpBarProgress
) {

}
