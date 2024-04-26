package smarthome.domain.log;

import java.time.LocalDateTime;
import java.util.UUID;
import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.Validator;

public class Log implements IAggregateRoot<LogID> {

  private LogID logID;
  private final DeviceID deviceID;
  private final SensorID sensorID;
  private final LocalDateTime localDateTime;
  private final ReadingValue reading;
  private final SensorTypeID description;
  private final UnitID unitID;

  /**
   * Constructs a new Log instance with the specified device ID, sensor ID, timestamp, and value.
   */
  Log(DeviceID deviceID, SensorID sensorID, LocalDateTime localDateTime, ReadingValue value,
      SensorTypeID description, UnitID unit) {
    Validator.validateNotNull(deviceID, "Device ID");
    this.deviceID = deviceID;
    Validator.validateNotNull(sensorID, "Sensor ID");
    this.sensorID = sensorID;
    Validator.validateNotNull(localDateTime, "Timestamp");
    this.localDateTime = localDateTime;
    Validator.validateNotNull(value, "Value");
    this.reading = value;
    Validator.validateNotNull(description, "Description");
    this.description = description;
    Validator.validateNotNull(unit, "Unit");
    this.unitID = unit;
    generateLogID();
  }

  /**
   * Constructs a new Log instance with the specified log ID, device ID, sensor ID, timestamp, and
   * value.
   */
  Log(LogID logID, DeviceID deviceID, SensorID sensorID, LocalDateTime localDateTime,
      ReadingValue value, SensorTypeID description, UnitID unit) {
    Validator.validateNotNull(logID, "Log ID");
    this.logID = logID;
    Validator.validateNotNull(deviceID, "Device ID");
    this.deviceID = deviceID;
    Validator.validateNotNull(sensorID, "Sensor ID");
    this.sensorID = sensorID;
    Validator.validateNotNull(localDateTime, "Timestamp");
    this.localDateTime = localDateTime;
    Validator.validateNotNull(value, "Value");
    this.reading = value;
    Validator.validateNotNull(description, "Description");
    this.description = description;
    Validator.validateNotNull(unit, "Unit");
    this.unitID = unit;
  }

  /**
   * Generate a new LogID object.
   */
  private void generateLogID() {
    logID = new LogID(UUID.randomUUID().toString());
  }

  /**
   * @return the _logID
   */
  @Override
  public LogID getID() {
    return logID;
  }

  /**
   * @return the _deviceID
   */
  public DeviceID getDeviceID() {
    return deviceID;
  }

  /**
   * @return the _sensorID
   */
  public SensorID getSensorID() {
    return sensorID;
  }

  /**
   * @return the _timeStamp
   */
  public LocalDateTime getTimeStamp() {
    return localDateTime;
  }

  /**
   * @return the _value
   */
  public ReadingValue getReadingValue() {
    return reading;
  }

  /**
   * @return the _description
   */
  public SensorTypeID getDescription() {
    return description;
  }

  /**
   * @return the _unit
   */
  public UnitID getUnit() {
    return unitID;
  }

  /**
   * Compares the current object with another object of the same type.
   *
   * @param obj is the object to be compared.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Log log) {
      return this.logID.equals(log.getID());
    }
    return false;
  }

  /**
   * Gets the hash code value of the object.
   *
   * @return the hash code value of the object.
   */
  @Override
  public int hashCode() {
    return logID.hashCode();
  }

  /**
   * Gets the string representation of the object.
   *
   * @return the string representation of the object.
   */
  @Override
  public String toString() {
    return logID + " " + deviceID + " " + sensorID + " " + localDateTime + " " + reading + " "
        + description
        + " " + unitID;
  }
}
