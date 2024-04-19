package smart_home.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockedConstruction;
import smart_home.domain.sensor_model.ISensorModelFactory;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.persistence.mem.SensorModelRepository;
import smart_home.value_object.ModelPath;
import smart_home.value_object.SensorModelName;
import smart_home.value_object.SensorTypeID;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class SensorModelServiceImplTest {
    /**
     * Test case for the constructor of the SensorModelService class.
     */
    @Test
    void shouldInstantiateSensorModelServiceWhenGivenValidParameters() {
        //Arrange
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        //Act
        SensorModelServiceImpl sensorModelServiceImpl = new SensorModelServiceImpl(sensorModelRepository, sensorModelFactory);
        //Assert
        assertNotNull(sensorModelServiceImpl);
    }

    /**
     * Should throw an exception when the sensor model factory is null.
     */
    @Test
    void shouldThrowExceptionWhenSensorModelFactoryIsNull() {
        //Arrange
        ISensorModelFactory sensorModelFactory = null;
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);

        String expectedMessage = "Please enter a valid sensor model factory.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorModelServiceImpl(sensorModelRepository, sensorModelFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Should throw an exception when the sensor model repository is null.
     */
    @Test
    void shouldThrowExceptionWhenSensorModelRepositoryIsNull() {
        //Arrange
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        SensorModelRepository sensorModelRepository = null;

        String expectedMessage = "Please enter a valid sensor model repository.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorModelServiceImpl(sensorModelRepository, sensorModelFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test case for the getAllSensorModels method.
     */
    @Test
    void shouldGetListOfSensorModel_WhenGetSensorModelsCalled() {
        //Arrange
        SensorModel sensorModel = mock(SensorModel.class);
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        when(sensorModelRepository.findAll()).thenReturn(List.of(sensorModel));
        try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
        });
             MockedConstruction<SensorModelName> sensorModelMockedConstruction = mockConstruction(SensorModelName.class, (mock, context) -> {
             })) {

            // Act
            SensorModelServiceImpl sensorModelServiceImpl = new SensorModelServiceImpl(sensorModelRepository, sensorModelFactory);
            List<SensorModel> sensorModels = sensorModelServiceImpl.getAllSensorModels();

            // Assert
            assertEquals(sensorModels, List.of(sensorModel));
        }
    }

    /**
     * Test case for the getSensorModel method.
     */
    @Test
    void shouldGetSensorModel_WhenGetSensorModelCalled() {
        //Arrange
        SensorModel sensorModel = mock(SensorModel.class);
        ModelPath sensorModelId = mock(ModelPath.class);
        when(sensorModel.getID()).thenReturn(sensorModelId);

        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        when(sensorModelRepository.ofIdentity(sensorModelId)).thenReturn(Optional.of(sensorModel));
        try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
        });
             MockedConstruction<SensorModelName> sensorModelMockedConstruction = mockConstruction(SensorModelName.class, (mock, context) -> {
             })) {

            // Act
            SensorModelServiceImpl sensorModelServiceImpl = new SensorModelServiceImpl(sensorModelRepository, sensorModelFactory);

            Optional<SensorModel> actualSensor = sensorModelServiceImpl.getSensorModel(sensorModelId);

            // Assert
            assertEquals(sensorModel, actualSensor.get());
        }
    }

    /**
     * Test case for the createSensorModel method.
     */
    @Test
    void shouldCreateSensorModel_WhenGivenValidParameters() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath sensorPath = mock(ModelPath.class);
        SensorModel sensorModel = mock(SensorModel.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        when(sensorModelFactory.createSensorModel(sensorModelName, sensorPath, sensorTypeID)).thenReturn(sensorModel);
        SensorModelServiceImpl sensorModelServiceImpl = new SensorModelServiceImpl(mock(SensorModelRepository.class), sensorModelFactory);
        //Act
        SensorModel actualSensorModel = sensorModelServiceImpl.createSensorModel(sensorModelName, sensorPath, sensorTypeID);
        //Assert
        assertEquals(sensorModel, actualSensorModel);
    }

    /**
     * Should activate the sensor model repository when the createSensorModel method is called.
      */
    @Test
    void shouldActivateSensorModelRepository_WhenCreateSensorModelCalled() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath sensorPath = mock(ModelPath.class);
        SensorModel sensorModel = mock(SensorModel.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        when(sensorModelFactory.createSensorModel(sensorModelName, sensorPath, sensorTypeID)).thenReturn(sensorModel);

        SensorModelServiceImpl sensorModelServiceImpl = new SensorModelServiceImpl(sensorModelRepository, sensorModelFactory);
        //Act
        sensorModelServiceImpl.createSensorModel(sensorModelName, sensorPath, sensorTypeID);
        //Assert
        verify(sensorModelRepository, times(1)).save(sensorModel);
    }


    /**
     * Test case for the getSensorModelsBySensorTypeId method.
     */
    @Test
    void shouldReturnSensorModelsBySensorTypeId() {
        // Arrange
        SensorModel sensorModel1 = mock(SensorModel.class);
        SensorModel sensorModel2 = mock(SensorModel.class);
        List<SensorModel> expectedSensorModels = Arrays.asList(sensorModel1, sensorModel2);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        when(sensorModelRepository.findBySensorTypeId(sensorTypeID)).thenReturn(expectedSensorModels);

        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        SensorModelServiceImpl sensorModelServiceImpl = new SensorModelServiceImpl(sensorModelRepository, sensorModelFactory);

        // Act
        List<SensorModel> actualSensorModels = sensorModelServiceImpl.getSensorModelsBySensorTypeId(sensorTypeID);

        // Assert
        assertEquals(expectedSensorModels, actualSensorModels);
    }


}

