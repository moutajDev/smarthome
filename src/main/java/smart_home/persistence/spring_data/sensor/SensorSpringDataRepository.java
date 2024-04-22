package smart_home.persistence.spring_data.sensor;

import smart_home.domain.repository.ISensorRepository;
import smart_home.domain.sensor.ISensor;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.SensorDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.SensorID;

import java.util.List;
import java.util.Optional;

public class SensorSpringDataRepository implements ISensorRepository {

    private ISensorSpringDataRepository _repository;

    private IDataModelAssembler<SensorDataModel, ISensor> _assembler;

    /**
     * Constructs a new repository instance with the specified entity manager factory and data model assembler.
     *
     * @param repository the repository to use
     * @param dataModelAssembler the converter to transform data models to domain models and vice versa
     */
    public SensorSpringDataRepository(ISensorSpringDataRepository repository, IDataModelAssembler<SensorDataModel, ISensor> dataModelAssembler) {
        Validator.validateNotNull(dataModelAssembler, "Data model assembler");
        _assembler = dataModelAssembler;
        Validator.validateNotNull(repository, "Repository");
        _repository = repository;
    }

    /**
     * Saves the specified sensor entity to the database.
     *
     * @param sensor the sensor entity to save
     * @return the saved sensor entity
     * @throws IllegalArgumentException if the entity is null
     */
    @Override
    public ISensor save(ISensor sensor) {
        Validator.validateNotNull(sensor, "Sensor");
        SensorDataModel sensorDataModel = new SensorDataModel(sensor);
        _repository.save(sensorDataModel);
        return sensor;
    }

    /**
     * Finds all sensor entities in the database.
     *
     * @return a list of all sensor entities
     */
    @Override
    public List<ISensor> findAll() {
        List<SensorDataModel> sensorDataModels = this._repository.findAll();
        List<ISensor> sensors = _assembler.toDomain(sensorDataModels);
        return sensors;
    }

    /**
     * Finds the sensor entity with the specified identity.
     *
     * @param objectID the identity of the sensor entity to find
     * @return the sensor entity with the specified identity
     */
    @Override
    public Optional<ISensor> ofIdentity(SensorID objectID) {
        Optional<SensorDataModel> sensorDataModel = this._repository.findById(objectID.getID());
        if (sensorDataModel.isPresent()) {
            SensorDataModel sensorDataModel1 = sensorDataModel.get();
            ISensor sensor = (ISensor) _assembler.toDomain(sensorDataModel1);
            return Optional.of(sensor);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Checks if the repository contains the specified sensor entity.
     *
     * @param objectID the identity of the sensor entity to check
     * @return true if the repository contains the sensor entity, false otherwise
     */
    @Override
    public boolean containsOfIdentity(SensorID objectID) {
        return this._repository.existsById(objectID.getID());
    }


}

