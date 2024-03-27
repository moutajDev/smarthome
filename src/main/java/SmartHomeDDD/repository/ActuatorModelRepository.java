package SmartHomeDDD.repository;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.ActuatorModel.ActuatorModel;
import SmartHomeDDD.valueObject.ActuatorModelID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ActuatorModelRepository implements Repository<ActuatorModelID, ActuatorModel> {

    private final Map<ActuatorModelID, ActuatorModel> _actuatorModelData = new LinkedHashMap<>();

    /**
     * Save an ActuatorModel. If the ActuatorModel is null, throw an IllegalArgumentException.
     *
     * @param actuatorModel is the domain entity to be saved.
     * @return the saved ActuatorModel.
     */
    @Override
    public ActuatorModel save(ActuatorModel actuatorModel) {
        if (actuatorModel == null) {
            throw new IllegalArgumentException("ActuatorModel cannot be null.");
        } else if (containsOfIdentity(actuatorModel.getID())) {
            throw new IllegalArgumentException("ActuatorModel already exists.");
        } else {
            _actuatorModelData.put(actuatorModel.getID(), actuatorModel);
        }
        return actuatorModel;
    }

    /**
     * Find all ActuatorModels.
     *
     * @return a list of all ActuatorModels.
     */
    @Override
    public List<ActuatorModel> findAll() {

        return List.copyOf(_actuatorModelData.values().stream().toList());
    }

    /**
     * Find an ActuatorModel by its identity.
     *
     * @param actuatorModelID is the unique identifier of the domain entity.
     * @return the ActuatorModel if found, otherwise Optional.empty().
     */
    @Override
    public Optional<ActuatorModel> ofIdentity(ActuatorModelID actuatorModelID) {

        return Optional.ofNullable(_actuatorModelData.get(actuatorModelID));
    }


    /**
     * Checks if an ActuatorModel exists by its identity.
     *
     * @param actuatorModelID is the unique identifier of the domain entity.
     * @return true if the ActuatorModel exists, otherwise false.
     */
    public boolean containsOfIdentity(ActuatorModelID actuatorModelID) {
        return _actuatorModelData.containsKey(actuatorModelID);

    }
}