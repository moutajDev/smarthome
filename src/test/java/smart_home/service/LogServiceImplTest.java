package smart_home.service;

import net.bytebuddy.asm.Advice.Local;
import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step.ForField.Read;
import org.junit.jupiter.api.Test;


import smart_home.domain.log.Log;
import smart_home.domain.repository.ILogRepository;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;
import smart_home.value_object.ReadingValue;
import smart_home.value_object.SensorTypeID;


import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LogServiceImplTest {

  /**
   * Test that the LogServiceImpl class can be instantiated.
   */
  @Test
  void shouldInstantiateValidLog() {
    // Arrange
    ILogRepository logRepositoryDouble = mock(ILogRepository.class);

    // Act
    LogServiceImpl result = new LogServiceImpl(logRepositoryDouble);

    // Assert
    assertNotNull(result);
  }

  /**
   * Test that the LogServiceImpl class throws an IllegalArgumentException when the LogRepository is
   * null.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenLogRepositoryIsNull() {
    // Arrange
    ILogRepository logRepository = null;
    String expectedMessage = "Log Repository is required";
    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new LogServiceImpl(logRepository));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the List of logs is returned
   */
  @Test
  void shouldReturnLogs_whenDeviceReadingsByTimePeriodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    DatePeriod period = mock(DatePeriod.class);

    Log log = mock(Log.class);
    Log log2 = mock(Log.class);

    List<Log> expectedLogs = List.of(log, log2);

    ILogRepository logRepository = mock(ILogRepository.class);
    when(logRepository.findByDeviceIDAndDatePeriodBetween(deviceID, period)).thenReturn(
        expectedLogs);

    LogServiceImpl logService = new LogServiceImpl(logRepository);

    // Act
    List<Log> actualLogs = logService.getDeviceReadingsByTimePeriod(deviceID, period);

    // Assert
    assertEquals(expectedLogs, actualLogs);
  }

  /**
   * Test if the List of logs is empty
   */
  @Test
  void shouldReturnEmptyList_whenDeviceReadingsByTimePeriodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    DatePeriod period = mock(DatePeriod.class);

    List<Log> expectedLogs = emptyList();

    ILogRepository logRepository = mock(ILogRepository.class);
    when(logRepository.findByDeviceIDAndDatePeriodBetween(deviceID, period)).thenReturn(
        expectedLogs);

    LogServiceImpl logService = new LogServiceImpl(logRepository);

    // Act
    List<Log> actualLogs = logService.getDeviceReadingsByTimePeriod(deviceID, period);

    // Assert
    assertEquals(expectedLogs, actualLogs);
  }

  /**
   * Test if the List of logs is returned not empty
   */
  @Test
  void shouldReturnLogs_whenDeviceReadingsBySensorTypeAndTimePeriodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    DatePeriod period = mock(DatePeriod.class);

    Log log = mock(Log.class);
    Log log2 = mock(Log.class);

    List<Log> expectedLogs = List.of(log, log2);

    ILogRepository logRepository = mock(ILogRepository.class);
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(deviceID, sensorTypeID, period))
        .thenReturn(expectedLogs);

    LogServiceImpl logService = new LogServiceImpl(logRepository);

    // Act
    List<Log> actualLogs = logService.getDeviceReadingsBySensorTypeAndTimePeriod(deviceID,
        sensorTypeID, period);

    // Assert
    assertEquals(expectedLogs, actualLogs);
  }

  /**
   * Test if the List of logs is empty
   */
  @Test
  void shouldThrowException_whenDeviceReadingsBySensorTypeAndTimePeriodIsCalledAndGetsEmptyList() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    DatePeriod period = mock(DatePeriod.class);

    List<Log> emptyLogs = emptyList();

    ILogRepository logRepository = mock(ILogRepository.class);
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(deviceID, sensorTypeID, period))
        .thenReturn(emptyLogs);

    LogServiceImpl logService = new LogServiceImpl(logRepository);

    String expectedMessage = "No readings found for the given time period";

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> logService.getDeviceReadingsBySensorTypeAndTimePeriod(deviceID, sensorTypeID, period));

    // Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for method getDifferenceBetweenReadings when the readings are within an interval of 5 minutes.
   */
  @Test
  void shouldReturnDifferenceBetweenReadings_whenGetDifferenceBetweenReadingsIsCalledAndReadingsAreWithin5MinutesInterval() {
    // Arrange
    /* Mocking one log object and adding it to a list*/
    ReadingValue readingValue1 = mock(ReadingValue.class);
    when(readingValue1.getReadingValue()).thenReturn("5");
    Log log1 = mock(Log.class);
    when(log1.getReadingValue()).thenReturn(readingValue1);
    when(log1.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    List<Log> list1 = List.of(log1);

    /* Mocking another log object and adding it to another list*/
    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getReadingValue()).thenReturn("14");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 5));

    List<Log> list2 = List.of(log2);


    ILogRepository logRepository = mock(ILogRepository.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository);

    int expectedDifference = 9;

    // Act
    List<Integer> actualDifference = logService.getDifferenceBetweenReadings(list1, list2);

    // Assert
    assertEquals(expectedDifference, actualDifference.get(0));
  }

  /**
   * Test for method getDifferenceBetweenReadings when multiple readings are within an interval of 5 minutes.
   */
  @Test
  void shouldReturnDifferenceBetweenReadings_whenGetDifferenceBetweenReadingsIsCalledAndMultipleReadingsAreWithin5MinutesInterval() {
    // Arrange
    /* Mocking one log object and adding it to a list*/
    ReadingValue readingValue1 = mock(ReadingValue.class);
    when(readingValue1.getReadingValue()).thenReturn("5");
    Log log1 = mock(Log.class);
    when(log1.getReadingValue()).thenReturn(readingValue1);
    when(log1.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    /* Mocking another log object and adding it to the second list*/
    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getReadingValue()).thenReturn("14");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 2));

    /* Mocking another log object and adding it to the second list*/
    ReadingValue readingValue3 = mock(ReadingValue.class);
    when(readingValue3.getReadingValue()).thenReturn("20");
    Log log3 = mock(Log.class);
    when(log3.getReadingValue()).thenReturn(readingValue3);
    when(log3.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 3));

    List<Log> list1 = List.of(log1);

    List<Log> list2 = List.of(log2, log3);

    ILogRepository logRepository = mock(ILogRepository.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository);

    int expectedDifference = 15;

    // Act
    List<Integer> actualDifference = logService.getDifferenceBetweenReadings(list1, list2);

    // Assert
    assertEquals(expectedDifference, actualDifference.get(1));

  }


  /**
   * Test for method getDifferenceBetweenReadings when the readings are not within an interval of 5 minutes.
   */
  @Test
  void shouldReturnEmptyList_whenGetDifferenceBetweenReadingsIsCalledAndReadingsAreNotWithin5MinutesInterval() {
    // Arrange
    /* Mocking one log object and adding it to a list*/
    ReadingValue readingValue1 = mock(ReadingValue.class);
    when(readingValue1.getReadingValue()).thenReturn("5");
    Log log1 = mock(Log.class);
    when(log1.getReadingValue()).thenReturn(readingValue1);
    when(log1.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    List<Log> list1 = List.of(log1);

    /* Mocking another log object and adding it to another list*/
    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getReadingValue()).thenReturn("14");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 6));

    List<Log> list2 = List.of(log2);

    ILogRepository logRepository = mock(ILogRepository.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository);

    // Act
    List<Integer> actualDifference = logService.getDifferenceBetweenReadings(list1, list2);

    // Assert
    assertTrue(actualDifference.isEmpty());
  }
}
