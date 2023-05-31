package ch.fhnw.graueenergie.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class EnergyEntityTest {

  private EnergyEntity entity;

  @BeforeEach
  void init() {
    entity = null;
  }

  @Nested
  class Consumer {

    @Test
    void setConsumerToNoConsumerTest() {
      entity = new EnergyEntity(EnergyBoardPosition.POS0, EnergyConsumer.NO_CONSUMER.id);

      assertEquals(EnergyConsumer.NO_CONSUMER, entity.getConsumer());
    }

    @Test
    void setConsumerToNonExistingConsumerTest() {
      int nonExistingConsumerId = 99999;

      entity = new EnergyEntity(EnergyBoardPosition.POS0, nonExistingConsumerId);

      assertEquals(EnergyConsumer.NO_CONSUMER, entity.getConsumer());
    }

    @Test
    void setConsumerToNoCityTest() {
      entity = new EnergyEntity(EnergyBoardPosition.POS0, EnergyConsumer.CITY.id);

      assertEquals(EnergyConsumer.CITY, entity.getConsumer());
    }

    @Test
    void setConsumerToEnergyProducerTest() {
      entity = new EnergyEntity(EnergyBoardPosition.POS0, EnergyProducer.WIND_POWER_PLANT.id);

      assertEquals(EnergyConsumer.NO_CONSUMER, entity.getConsumer());
    }
  }

  @Nested
  class Producer {

    @Nested
    class WithoutProducer {

      @BeforeEach
      void initWithoutProducer() {
        entity = new EnergyEntity(EnergyBoardPosition.POS1, EnergyProducer.NO_ENERGY_PRODUCER.id);
      }

      @Test
      void setProducerToNoProducerTest() {
        assertEquals(EnergyProducer.NO_ENERGY_PRODUCER, entity.getProducer());
      }

      @Test
      void getEnergyProducedTest() {
        assertEquals(0, entity.getEnergyProduced());
      }

      @Test
      void getUbpPerEnergyProducedTest() {
        assertEquals(0, entity.getUbpPerEnergyProduced());
      }
    }


    @Test
    void setProducerToNonExistingProducerTest() {
      int nonExistingProducerId = 99999;

      entity = new EnergyEntity(EnergyBoardPosition.POS1, nonExistingProducerId);

      assertEquals(EnergyProducer.NO_ENERGY_PRODUCER, entity.getProducer());
    }

    @Nested
    class WithProducer {

      private final EnergyProducer producer = EnergyProducer.WIND_POWER_PLANT;
      private final EnergyBoardPosition position = EnergyBoardPosition.POS1;

      @BeforeEach
      void initProducer() {
        entity = new EnergyEntity(position, producer.id);
      }

      @Test
      void setProducerToProducerTest() {
        assertEquals(EnergyProducer.WIND_POWER_PLANT, entity.getProducer());
      }

      @Test
      void getEnergyProducedTest() {
        int energyProduced = (int) (producer.energyProduced * position.energyDeclineFromDistance);

        assertEquals(energyProduced, entity.getEnergyProduced());
      }

      @Test
      void getUbpPerEnergyProducedTest() {
        int ubpPerEnergy = producer.energyProduced * producer.ubpPerEnergyProduced;

        assertEquals(ubpPerEnergy, entity.getUbpPerEnergyProduced());
      }
    }
  }
}
