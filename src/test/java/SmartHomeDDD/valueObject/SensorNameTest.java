package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorNameTest {
    /**
     * Tests the SensorName constructor with a valid sensor name.
     */
    @Test
    void shouldGetValidObject_WhenUsingValidSensorName(){
        // Arrange
        String validSensorName = "Temperature Sensor 1";

        // Act
        SensorName result = new SensorName(validSensorName);

        // Assert
        assertNotNull(result);
    }

    /**
     * Tests the SensorName constructor with a null sensor name.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorNameNull(){
        // Arrange
        String nullSensorName = null;
        String expectedMessage = "The sensor name cannot be null, blank, or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorName(nullSensorName)
        );

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Tests the SensorName constructor with a blank sensor name.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorNameBlank(){
        // Arrange
        String blankSensorName = " ";
        String expectedMessage = "The sensor name cannot be null, blank, or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorName(blankSensorName)
        );

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Tests the SensorName constructor with an empty sensor name.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorNameEmpty(){
        // Arrange
        String emptySensorName = "";
        String expectedMessage = "The sensor name cannot be null, blank, or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorName(emptySensorName)
        );

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Tests the SensorName constructor with special characters in the sensor name.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorNameContainsSpecialCharacters(){
        // Arrange
        String invalidSensorName = "Temperature Sensor 1!";
        String expectedMessage = "The sensor name can only contain letters and numbers.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorName(invalidSensorName)
        );

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Tests the getSensorName method.
     */
    @Test
    void shouldGetSensorName_WhenGetSensorNameIsCalled(){
        // Arrange
        String sensorName = "Temperature Sensor 1";
        SensorName sensorNameObject = new SensorName(sensorName);

        // Act
        String result = sensorNameObject.getSensorName();

        // Assert
        assertEquals(sensorName, result);
    }

    /**
     * Tests the equals method when the same instance is compared.
     */
    @Test
    void shouldReturnTrue_WhenSameInstanceIsCompared(){
        // Arrange
        String sensorName = "Temperature Sensor 1";
        SensorName sensorNameObject = new SensorName(sensorName);

        // Act
        boolean result = sensorNameObject.equals(sensorNameObject);

        // Assert
        assertTrue(result, "A SensorName object compared to itself should return true.");
    }


    /**
     * Tests the equals method when the instances are equal.
     */
    @Test
    void shouldReturnTrue_WhenInstancesAreEqual(){
        // Arrange
        String sensorName = "Temperature Sensor 1";
        SensorName sensorNameObject = new SensorName(sensorName);
        SensorName sensorNameObject2 = new SensorName(sensorName);

        // Act
        boolean result = sensorNameObject.equals(sensorNameObject2);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests the equals method when the instances are not equal.
     */
    @Test
    void shouldReturnFalse_WhenInstancesAreNotEqual(){
        // Arrange
        String sensorName = "Temperature Sensor 1";
        String sensorName2 = "Temperature Sensor 2";
        SensorName sensorNameObject = new SensorName(sensorName);
        SensorName sensorNameObject2 = new SensorName(sensorName2);

        // Act
        boolean result = sensorNameObject.equals(sensorNameObject2);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests the equals method to compare a null object with a SensorName object.
     */
    @Test
    void shouldReturnFalse_WhenComparedAnObjectWithNull(){
        // Arrange
        String sensorName = "Temperature Sensor";
        SensorName sensorNameObject = new SensorName(sensorName);

        // Act
        boolean result = sensorNameObject.equals(null);

        // Assert
        assertFalse(result, "Comparing a SensorName object with null should return false.");
    }


    /**
     * Tests the toString method.
     */
    @Test
    void shouldReturnStringRepresentation_WhenToStringIsCalled(){
        // Arrange
        String sensorName = "Temperature Sensor 1";
        SensorName sensorNameObject = new SensorName(sensorName);

        // Act
        String result = sensorNameObject.toString();

        // Assert
        assertTrue(result.contains(sensorName));
    }
}