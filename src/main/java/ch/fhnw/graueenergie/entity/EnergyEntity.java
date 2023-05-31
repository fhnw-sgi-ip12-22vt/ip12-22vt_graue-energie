package ch.fhnw.graueenergie.entity;

import java.util.Objects;

public final class EnergyEntity {

  private final EnergyProducer producer;
  private final EnergyBoardPosition boardPosition;
  private EnergyConsumer consumer;

  public EnergyEntity(EnergyBoardPosition boardPosition, int energyActorId) {
    boolean isEnergyActorOnCorrectType = boardPosition.isEnergyActorAllowedOnType(energyActorId);

    if (boardPosition.positionId == EnergyBoardPosition.POS0.positionId) {
      this.consumer = isEnergyActorOnCorrectType ? EnergyConsumer.valueOf(energyActorId)
          : EnergyConsumer.NO_CONSUMER;
      this.producer = EnergyProducer.NO_ENERGY_PRODUCER;
    } else {
      this.producer = isEnergyActorOnCorrectType ? EnergyProducer.valueOf(energyActorId)
          : EnergyProducer.NO_ENERGY_PRODUCER;
    }

    this.boardPosition = boardPosition;
  }

  public EnergyConsumer getConsumer() {
    return consumer;
  }

  public int getEnergyProduced() {
    return (int) (producer.energyProduced * boardPosition.energyDeclineFromDistance);
  }

  public int getUbpPerEnergyProduced() {
    return producer.energyProduced * producer.ubpPerEnergyProduced;
  }

  public EnergyBoardPosition getBoardPosition() {
    return boardPosition;
  }

  public EnergyProducer getProducer() {
    return producer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnergyEntity that = (EnergyEntity) o;
    return producer == that.producer && consumer == that.consumer
        && boardPosition == that.boardPosition;
  }

  @Override
  public int hashCode() {
    return Objects.hash(producer, consumer, boardPosition);
  }

  public EnergyActor getEnergyActor() {
    return consumer != null ? consumer : producer;
  }
}
