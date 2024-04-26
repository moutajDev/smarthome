package smarthome.persistence.jpa.data_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smarthome.domain.actuator.IActuator;
import smarthome.utils.Validator;

@Entity
@Table(name = "Actuator")
public class ActuatorDataModel {

  @Id
  private String _actuatorID;
  @Column(name = "deviceID")
  private String _deviceID;
  @Column(name = "modelPath")
  private String _modelPath;
  @Column(name = "actuatorTypeID")
  private String _actuatorTypeID;
  @Column(name = "actuatorName")
  private String _actuatorName;
  @Column(name = "integerLowerBond")
  private String _integerLowerBond;
  @Column(name = "integerUpperBond")
  private String _integerUpperBond;
  @Column(name = "decimalLowerBond")
  private String _decimalLowerBond;
  @Column(name = "decimalUpperBond")
  private String _decimalUpperBond;
  @Version
  private long version;


  public ActuatorDataModel() {
  }

  /**
   * Class constructor
   */
  public ActuatorDataModel(IActuator actuator) {
    Validator.validateNotNull(actuator, "Actuator");
    setGenericActuatorParameters(actuator);

  }

  /**
   * Set the generic actuator parameters to the data model
   *
   * @param actuator
   */
  //Setters
  public void setGenericActuatorParameters(IActuator actuator) {
    this._actuatorID = actuator.getID().getID();
    this._deviceID = actuator.getDeviceID().getID();
    this._modelPath = actuator.getModelPath().getID();
    this._actuatorTypeID = actuator.getActuatorTypeID().getID();
    this._actuatorName = actuator.getName().getActuatorName();
  }

  /**
   * Method to return the integer lower bond
   */
  public String getIntegerLowerBond() {
    return this._integerLowerBond;
  }

  /**
   * Set the integer lower bond from actuators that have this specification
   *
   * @param integerLowerBond
   */

  public void setIntegerLowerBond(int integerLowerBond) {
    this._integerLowerBond = String.valueOf(integerLowerBond);
  }

  /**
   * Method to return the integer upper bond
   */
  public String getIntegerUpperBond() {
    return this._integerUpperBond;
  }

  /**
   * Set the integer upper bond from actuators that have this specification
   *
   * @param integerUpperBond
   */
  public void setIntegerUpperBond(int integerUpperBond) {
    this._integerUpperBond = String.valueOf(integerUpperBond);
  }
  // This section is for getter methods

  /**
   * Method to return the decimal lower bond
   */
  public String getDecimalLowerBond() {
    return this._decimalLowerBond;
  }

  /**
   * set de lower decimal bond from actuators that have this specification
   *
   * @param decimalLowerBond
   */
  public void setDecimalLowerBond(double decimalLowerBond) {
    this._decimalLowerBond = String.valueOf(decimalLowerBond);
  }

  /**
   * Method to return the decimal upper bond
   */
  public String getDecimalUpperBond() {
    return this._decimalUpperBond;
  }

  /**
   * Set the upper decimal bond from actuators that have this specification
   *
   * @param decimalUpperBond
   */
  public void setDecimalUpperBond(double decimalUpperBond) {
    this._decimalUpperBond = String.valueOf(decimalUpperBond);
  }

  /**
   * Method to return the actuator ID.
   *
   * @return the actuator ID
   */
  public String getActuatorID() {
    return this._actuatorID;
  }

  /**
   * Method to return the device ID.
   *
   * @return the device ID
   */
  public String getDeviceID() {
    return this._deviceID;
  }

  /**
   * Method to return the model path.
   *
   * @return the model path
   */
  public String getModelPath() {
    return this._modelPath;
  }

  /**
   * Method to return the actuator type ID.
   *
   * @return the actuator type ID
   */
  public String getActuatorTypeID() {
    return this._actuatorTypeID;
  }

  /**
   * Method to return the actuator name.
   *
   * @return the actuator name
   */
  public String getActuatorName() {
    return this._actuatorName;
  }

  @Override
  public String toString() {
    return "ActuatorDataModel{" +
        "actuatorID='" + _actuatorID + '\'' +
        ", deviceID='" + _deviceID + '\'' +
        ", modelPath='" + _modelPath + '\'' +
        ", actuatorTypeID='" + _actuatorTypeID + '\'' +
        ", actuatorName='" + _actuatorName + '\'' +
        ", integerLowerBond='" + _integerLowerBond + '\'' +
        ", integerUpperBond='" + _integerUpperBond + '\'' +
        ", decimalLowerBond='" + _decimalLowerBond + '\'' +
        ", decimalUpperBond='" + _decimalUpperBond + '\'' +
        '}';
  }

}
