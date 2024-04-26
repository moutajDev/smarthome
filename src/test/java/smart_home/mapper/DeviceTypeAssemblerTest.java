package smart_home.mapper;

import org.junit.jupiter.api.Test;
import smart_home.domain.device_type.DeviceType;
import smart_home.dto.DeviceTypeDTO;
import smart_home.value_object.DeviceTypeID;
import smart_home.value_object.TypeDescription;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class DeviceTypeAssemblerTest {

  /**
   * Test if the domainToDTO method returns a DeviceTypeDTO object when the device type is valid.
   */
  @Test
  void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeIsValid() {
    // Arrange
    String deviceTypeID = "Switch Device";
    String deviceTypeDescription = "Switch Device Description";

    DeviceType deviceType = mock(DeviceType.class);
    when(deviceType.getID()).thenReturn(mock(DeviceTypeID.class));
    when(deviceType.getID().toString()).thenReturn(deviceTypeID);
    when(deviceType.getDescription()).thenReturn(mock(TypeDescription.class));
    when(deviceType.getDescription().toString()).thenReturn(deviceTypeDescription);

    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
    String expected = deviceTypeID + " " + deviceTypeDescription;
    // Act
    DeviceTypeDTO deviceTypeDTO = deviceTypeAssembler.domainToDTO(deviceType);
    // Assert
    assertEquals(expected, deviceTypeDTO.toString());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the DeviceType is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceTypeIsNull() {
    // Arrange
    DeviceType deviceType = null;
    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();

    String expectedMessage = "DeviceType is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {deviceTypeAssembler.domainToDTO(deviceType);});
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the list of DeviceTypes is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceTypeListIsNull() {
    // Arrange
    List<DeviceType> deviceTypes = null;
    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();

    String expectedMessage = "The list of DeviceTypes cannot be null, empty";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {deviceTypeAssembler.domainToDTO(deviceTypes);});
    assertEquals(expectedMessage, exception.getMessage());

  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the list of DeviceTypes is empty.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceTypeListIsEmpty() {
    // Arrange
    List<DeviceType> deviceTypes = List.of();
    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();

    String expectedMessage = "The list of DeviceTypes cannot be null, empty";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      deviceTypeAssembler.domainToDTO(deviceTypes);
    });
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the domainToDTO method returns a list of DeviceTypeDTO objects when the list of device types is valid.
   */
  @Test
  void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeListIsValid() {
    // Arrange
    String deviceTypeID = "Switch Device";
    String deviceTypeDescription = "Switch Device Description";

    DeviceType deviceType = mock(DeviceType.class);
    when(deviceType.getID()).thenReturn(mock(DeviceTypeID.class));
    when(deviceType.getID().toString()).thenReturn(deviceTypeID);
    when(deviceType.getDescription()).thenReturn(mock(TypeDescription.class));
    when(deviceType.getDescription().toString()).thenReturn(deviceTypeDescription);

    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
    List<DeviceType> deviceTypes = Arrays.asList(deviceType);
    DeviceTypeDTO expectedDeviceType = new DeviceTypeDTO(deviceTypeID, deviceTypeDescription);
    List<DeviceTypeDTO> expected = List.of(expectedDeviceType);

    // Act
    List<DeviceTypeDTO> deviceTypeDTOS = deviceTypeAssembler.domainToDTO(deviceTypes);

    // Assert
    assertEquals(expected.toString(), deviceTypeDTOS.toString());
  }
}
