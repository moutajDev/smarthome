package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class ActuatorNameTest {

    @Test
    void shouldInstantiateANewActuatorName() {
        //Arrange
        String actuatorName = "Switch Actuator";

        //Act
        ActuatorName result = new ActuatorName(actuatorName);

        //Assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenActuatorNameIsNull() {

        String actuatorName = null;
        String expectedMessage = "The actuator name cannot be null, blank, or empty.";

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorName(actuatorName)
        );

        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowIllegalArgumentException_whenActuatorNameIsBlank() {

        String actuatorName = " ";
        String expectedMessage = "The actuator name cannot be null, blank, or empty.";

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorName(actuatorName)
        );

        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowIllegalArgumentException_whenActuatorNameIsEmpty() {

        String actuatorName = "";
        String expectedMessage = "The actuator name cannot be null, blank, or empty.";

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorName(actuatorName)
        );

        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowIllegalArgumentException_whenActuatorNameContainsSpecialCharacters() {

        String actuatorName = "Switch Actuator!";
        String expectedMessage = "The actuator name can only contain letters and numbers.";

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorName(actuatorName)
        );

        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    @Test
    void shouldGetActuatorName() {

        String actuatorName = "Switch Actuator";
        ActuatorName actuatorNameVO = new ActuatorName(actuatorName);

        String result = actuatorNameVO.getActuatorName();

        assertTrue(result.equals(actuatorName));
    }

}
