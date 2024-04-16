package smartHome.persistence.jpa.repository;

import jakarta.persistence.*;
import smartHome.domain.actuatorModel.ActuatorModel;
import smartHome.domain.repository.IActuatorModelRepository;
import smartHome.persistence.assembler.IDataModelAssembler;
import smartHome.persistence.jpa.dataModel.ActuatorModelDataModel;
import smartHome.valueObject.ActuatorTypeID;
import smartHome.valueObject.ModelPath;

import java.util.List;
import java.util.Optional;

public class RepositoryActuatorModelJPAImpl implements IActuatorModelRepository {
  private EntityManagerFactory _factory;
  private IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> _dataModelConverter;

  /**
   * RepositoryActuatorModelJPAImpl constructor
   *
   * @param dataModelConverter
   */
  public RepositoryActuatorModelJPAImpl(
      IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> dataModelConverter) {
    validateDataModelConverter(dataModelConverter);
    _factory = Persistence.createEntityManagerFactory("smartHome");
    _dataModelConverter = dataModelConverter;
  }

  /**
   * Method to validate actuator model data model converter
   *
   * @param entity IDataModelAssembler<ActuatorModelDataModel, ActuatorModel>
   */
  private void validateDataModelConverter(
      IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Data model converter cannot be null");
    }
  }

  /**
   * Method to get entity manager
   *
   * @return EntityManager
   */
  private EntityManager getEntityManager() {
    EntityManager manager = _factory.createEntityManager();
    return manager;
  }

  /**
   * Method to save actuator model
   *
   * @param actuatorModel is the domain entity to be saved.
   * @return ActuatorModel
   */
  @Override
  public ActuatorModel save(ActuatorModel actuatorModel) {
    if (actuatorModel == null) {
      throw new IllegalArgumentException("The provided entity must not be null.");
    }
    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModel);
    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(actuatorModelDataModel);
      tx.commit();
    } catch (RuntimeException e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
    return actuatorModel;
  }

  /**
   * Method to find all actuator models
   *
   * @return List<ActuatorModel>
   */
  @Override
  public List<ActuatorModel> findAll() {
    EntityManager entityManager = getEntityManager();
    try {
      Query query =
          entityManager.createQuery(
              "SELECT ACTUATOR_MODEL FROM ActuatorModelDataModel ACTUATOR_MODEL");
      List<ActuatorModelDataModel> listDataModel = query.getResultList();
      List<ActuatorModel> listDomain = _dataModelConverter.toDomain(listDataModel);
      return listDomain;
    } finally {
      entityManager.close();
    }
  }

  /**
   * Method to find actuator model by identity
   *
   * @param actuatorModelID ModelPath
   * @return Optional<ActuatorModel>
   */
  @Override
  public Optional<ActuatorModel> ofIdentity(ModelPath actuatorModelID) {
    EntityManager entityManager = getEntityManager();
    try {
      ActuatorModelDataModel actuatorModelDataModel =
          entityManager.find(ActuatorModelDataModel.class, actuatorModelID);
      if (actuatorModelDataModel == null) {
        return Optional.empty();
      } else {
        ActuatorModel actuatorModel = _dataModelConverter.toDomain(actuatorModelDataModel);
        return Optional.of(actuatorModel);
      }
    } finally {
      entityManager.close();
    }
  }

  /**
   * Method to check if actuator model contains identity
   *
   * @param actuatorModelID ModelPath
   * @return boolean
   */
  @Override
  public boolean containsOfIdentity(ModelPath actuatorModelID) {
    Optional<ActuatorModel> actuatorModel = ofIdentity(actuatorModelID);
    return actuatorModel.isPresent();
  }

  /**
   * Method to find actuator model by actuator type id
   *
   * @param actuatorTypeID ActuatorTypeID
   * @return List<ActuatorModel>
   */
  @Override
  public List<ActuatorModel> findByActuatorTypeId(ActuatorTypeID actuatorTypeID) {
    EntityManager entityManager = getEntityManager();
    try {
      Query query =
          entityManager.createQuery(
              "SELECT ACTUATOR_MODEL FROM ActuatorModelDataModel ACTUATOR_MODEL WHERE ACTUATOR_MODEL._actuatorTypeID = :actuatorTypeID");
      query.setParameter("actuatorTypeID", actuatorTypeID.getID());
      List<ActuatorModelDataModel> listDataModel = query.getResultList();
      return _dataModelConverter.toDomain(listDataModel);
    } finally {
      entityManager.close();
    }
  }
}
