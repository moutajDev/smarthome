package smarthome.mapper;

import java.util.List;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.utils.Validator;
import smarthome.utils.dto.ActuatorModelDTO;

public class ActuatorModelAssembler implements IAssembler<ActuatorModel, ActuatorModelDTO> {

  /**
   * Converts an ActuatorModel domain entity to an ActuatorModelDTO data transfer object.
   *
   * @param domainEntity is the domain entity to be converted.
   * @return The ActuatorModelDTO data transfer object.
   */
  @Override
  public ActuatorModelDTO domainToDTO(ActuatorModel domainEntity) {
    Validator.validateNotNull(domainEntity, "Actuator Model");

    String actuatorModelID = domainEntity.getID().toString();
    String actuatorModelName = domainEntity.getActuatorModelName().toString();
    String actuatorModelPath = domainEntity.getID().toString();

    ActuatorModelDTO actuatorModelDTO = new ActuatorModelDTO(actuatorModelID, actuatorModelName,
        actuatorModelPath);
    return actuatorModelDTO;
  }

  /**
   * Converts a list of ActuatorModel domain entities to a list of ActuatorModelDTO data transfer
   * objects.
   *
   * @param domainEntities is the list of domain entities to be converted.
   * @return The list of ActuatorModelDTO data transfer objects.
   */
  @Override
  public List<ActuatorModelDTO> domainToDTO(List<ActuatorModel> domainEntities) {
    if (domainEntities == null || domainEntities.isEmpty()) {
      throw new IllegalArgumentException("The list of Actuator Models cannot be null or empty.");
    }
    List<ActuatorModelDTO> actuatorModelsDTO = domainEntities.stream().map(this::domainToDTO)
        .toList();
    return actuatorModelsDTO;
  }
}
