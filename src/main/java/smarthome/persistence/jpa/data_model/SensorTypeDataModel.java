package smarthome.persistence.jpa.data_model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smarthome.domain.sensor_type.SensorType;
import smarthome.utils.Validator;

@Entity
@Table(name = "Sensor_Type")
public class SensorTypeDataModel {

  @Id
  private String sensorTypeID;
  private String typeDescription;
  private String unitID;
  @Version
  private long version;

  /**
   * Default constructor
   */
  public SensorTypeDataModel() {
  }

  /**
   * Constructor of the sensor type data model
   *
   * @param sensorType the sensor type
   */
  public SensorTypeDataModel(SensorType sensorType) {
    Validator.validateNotNull(sensorType, "Sensor Type");
    this.sensorTypeID = sensorType.getID().getID();
    this.typeDescription = sensorType.getName().getID();
    this.unitID = sensorType.getUnitID().getID();
  }


  /**
   * Get the sensor type ID
   *
   * @return the sensor type ID
   */
  public String getSensorTypeID() {
    return this.sensorTypeID;
  }

  /**
   * Get the type description
   *
   * @return the type description
   */
  public String getTypeDescription() {
    return this.typeDescription;
  }

  /**
   * Get the unit ID
   *
   * @return the unit ID
   */
  public String getUnitID() {
    return this.unitID;
  }


}
