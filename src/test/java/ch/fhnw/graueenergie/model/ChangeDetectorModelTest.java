package ch.fhnw.graueenergie.model;

/*
class ChangeDetectorModelTest {

  EnergyEntity[] energyEntities;
  EnergyEntity[] newStateOfEnergyEntities;
  ChangeDetectorModel model;

  @BeforeEach
  void init() {
    energyEntities = createEmptyEnergyEntities();
    newStateOfEnergyEntities = energyEntities;

    model = new ChangeDetectorModel(energyEntities);
  }

  @Test
  void createModelTest() {
    assertNotNull(model);
  }

  @Test
  void changeStateOfOneEntityTest() {
    newStateOfEnergyEntities[1] = new EnergyEntity(EnergyBoardPosition.POS1,
        EnergyProducer.WIND_POWER_PLANT.id);

    model.stateChanged(newStateOfEnergyEntities);

    assertEquals(1, model.getLastChanges().length);
    assertEquals(newStateOfEnergyEntities[1], model.getLastChanges()[0]);
  }

  @Test
  void changeStateOfTwoEntitiesTest() {
    newStateOfEnergyEntities[1] = new EnergyEntity(EnergyBoardPosition.POS1,
        EnergyProducer.WIND_POWER_PLANT.id);
    newStateOfEnergyEntities[2] = new EnergyEntity(EnergyBoardPosition.POS1,
        EnergyProducer.WIND_POWER_PLANT.id);

    model.stateChanged(newStateOfEnergyEntities);

    assertEquals(2, model.getLastChanges().length);
    assertEquals(newStateOfEnergyEntities[1], model.getLastChanges()[0]);
    assertEquals(newStateOfEnergyEntities[2], model.getLastChanges()[1]);
  }

  @Nested
  class ModelWithChangedState {

    @BeforeEach
    void changeState() {
      newStateOfEnergyEntities[0] = new EnergyEntity(EnergyBoardPosition.POS0,
          EnergyConsumer.CITY.id);
      newStateOfEnergyEntities[1] = new EnergyEntity(EnergyBoardPosition.POS1,
          EnergyProducer.WIND_POWER_PLANT.id);

      model.stateChanged(newStateOfEnergyEntities);
    }

    @Test
    void changeStateToNoConsumerTest() {
      EnergyEntity[] newEnergyEntitiesState = newStateOfEnergyEntities.clone();
      newEnergyEntitiesState[1] = new EnergyEntity(EnergyBoardPosition.POS1,
          EnergyConsumer.NO_CONSUMER.id);

      model.stateChanged(newEnergyEntitiesState);

      assertEquals(1, model.getLastChanges().length);
      assertEquals(newEnergyEntitiesState[1], model.getLastChanges()[0]);
    }

    @Test
    void changeStateToConsumerTest() {
      EnergyEntity[] newEnergyEntitiesState = newStateOfEnergyEntities.clone();
      newEnergyEntitiesState[1] = new EnergyEntity(EnergyBoardPosition.POS1,
          EnergyConsumer.VILLAGE.id);

      model.stateChanged(newEnergyEntitiesState);

      assertEquals(1, model.getLastChanges().length);
      assertEquals(newEnergyEntitiesState[1], model.getLastChanges()[0]);
    }

    @Test
    void changeStateToNoProducerTest() {
      EnergyEntity[] newEnergyEntitiesState = newStateOfEnergyEntities.clone();
      newEnergyEntitiesState[1] = new EnergyEntity(EnergyBoardPosition.POS1,
          EnergyProducer.NO_ENERGY_PRODUCER.id);

      model.stateChanged(newEnergyEntitiesState);

      assertEquals(1, model.getLastChanges().length);
      assertEquals(newEnergyEntitiesState[1], model.getLastChanges()[0]);
    }

    @Test
    void changeStateToProducerTest() {
      EnergyEntity[] newEnergyEntitiesState = newStateOfEnergyEntities.clone();
      newEnergyEntitiesState[1] = new EnergyEntity(EnergyBoardPosition.POS1,
          EnergyProducer.COAL_FIRED_POWER_STATION.id);

      model.stateChanged(newEnergyEntitiesState);

      assertEquals(1, model.getLastChanges().length);
      assertEquals(newEnergyEntitiesState[1], model.getLastChanges()[0]);
    }
  }


  EnergyEntity[] createEmptyEnergyEntities() {
    EnergyEntity[] entities = new EnergyEntity[EnergyBoardPosition.values().length];
    for (int i = 0; i < entities.length; i++) {
      entities[i] = new EnergyEntity(EnergyBoardPosition.valueOf(i),
          EnergyProducer.NO_ENERGY_PRODUCER.id);
    }

    return entities;
  }
}

 */
