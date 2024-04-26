package smarthome.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.device.Device;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.sensor.ISensorFactory;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.mem.DeviceRepository;
import smarthome.persistence.mem.SensorRepository;


class SensorServiceImplTest {

  /**
   * Test method shouldInstantiateSensorService_whenGivenValidParameters
   */
  @Test
  void shouldInstantiateSensorService_whenGivenValidParameters() {
    // Arrange
    SensorServiceImpl sensorServiceImpl;
    SensorRepository sensorRepository = mock(SensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    DeviceRepository deviceRepository = mock(DeviceRepository.class);

    // Act
    sensorServiceImpl = new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository);

    // Assert
    assertNotNull(sensorServiceImpl);
  }


  /**
   * Should throw an exception when the sensor repository is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullSensorRepository() {
    // Arrange
    SensorRepository sensorRepository = null;
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    DeviceRepository deviceRepository = mock(DeviceRepository.class);

    // Act Assert
    assertThrows(IllegalArgumentException.class,
        () -> new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository));
  }

  /**
   * Should throw an exception when the sensor factory is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullSensorFactory() {
    // Arrange
    SensorRepository sensorRepository = mock(SensorRepository.class);
    ISensorFactory sensorFactory = null;
    DeviceRepository deviceRepository = mock(DeviceRepository.class);

    // Act Assert
    assertThrows(IllegalArgumentException.class,
        () -> new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository));
  }

  /**
   * Should throw an exception when the device repository is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullDeviceRepository() {
    // Arrange
    SensorRepository sensorRepository = mock(SensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    DeviceRepository deviceRepository = null;

    // Act Assert
    assertThrows(IllegalArgumentException.class,
        () -> new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository));
  }

  /**
   * should add sensor when device is found
   */
  @Test
  void shouldAddSensor_whenDeviceIsFound() {
    // Arrange
    SensorRepository sensorRepository = mock(SensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    DeviceRepository deviceRepository = mock(DeviceRepository.class);

    SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(sensorRepository, sensorFactory,
        deviceRepository);

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");
    SensorName sensorName = new SensorName("sensorName");
    Device mockDevice = mock(Device.class);
    DeviceStatus mockDeviceStatus = mock(DeviceStatus.class);

    when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.of(mockDevice));
    when(mockDevice.getDeviceStatus()).thenReturn(mockDeviceStatus);
    when(mockDeviceStatus.getStatus()).thenReturn(true);

    ISensor mockSensor = mock(ISensor.class);

    when(sensorFactory.create(deviceID, modelPath, sensorTypeID, sensorName)).thenReturn(
        mockSensor);

    // Act
    ISensor actualSensor = sensorServiceImpl.addSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    // Assert
    assertNotNull(actualSensor);
    assertEquals(mockSensor, actualSensor);
  }

  /**
   * Test method addSensor To trow exception when device not found
   */
  @Test
  void shouldThrowException_whenDeviceNotFound() {
    // Arrange
    SensorRepository sensorRepository = mock(SensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    DeviceRepository deviceRepository = mock(DeviceRepository.class);

    SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(sensorRepository, sensorFactory,
        deviceRepository);

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");
    SensorName sensorName = new SensorName("sensorName");

    when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.empty());

    String expectedMessage = "Device with ID " + deviceID + " not found.";

    // Act Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorServiceImpl.addSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test method addSensor To trow exception when device is deactivated
   */
  @Test
  void shouldThrowException_whenDeviceIsOff() {
    // Arrange
    SensorRepository sensorRepository = mock(SensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    DeviceRepository deviceRepository = mock(DeviceRepository.class);

    SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(sensorRepository, sensorFactory,
        deviceRepository);

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");
    SensorName sensorName = new SensorName("sensorName");
    Device mockDevice = mock(Device.class);
    DeviceStatus mockDeviceStatus = mock(DeviceStatus.class);

    when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.of(mockDevice));
    when(mockDevice.getDeviceStatus()).thenReturn(mockDeviceStatus);
    when(mockDeviceStatus.getStatus()).thenReturn(false);

    // Act Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorServiceImpl.addSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    assertEquals("Device with ID " + deviceID + " is deactivated.", exception.getMessage());
  }
}
