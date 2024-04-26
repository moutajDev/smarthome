package smarthome.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.house.House;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.persistence.mem.HouseRepository;
import smarthome.persistence.mem.RoomRepository;

class RoomServiceImplTest {

  /**
   * Test the constructor of the RoomService class.
   */
  @Test
  void shouldInstantiateRoomService_whenGivenValidParameters() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    RoomRepository roomRepository = mock(RoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    HouseRepository houseRepository = mock(HouseRepository.class);

    // Act
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    // Assert
    assertNotNull(roomServiceImpl);
  }

  /**
   * Test the addRoom method of the RoomService class with a valid houseID, roomName, dimension and
   * roomFloor.
   */
  @Test
  void shouldAddARoom_WhenGivenValidParameters() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    RoomRepository roomRepository = mock(RoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    HouseRepository houseRepository = mock(HouseRepository.class);
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);
    House mockHouse = mock(House.class);
    Room mockRoom = mock(Room.class);
    when(houseRepository.ofIdentity(any(HouseID.class))).thenReturn(Optional.of(mockHouse));
    when(roomFactory.createRoom(any(HouseID.class), any(RoomName.class), any(Dimension.class),
        any(RoomFloor.class))).thenReturn(mockRoom);

    // Act
    Room room = roomServiceImpl.addRoom(houseID, roomName, dimension, roomFloor);
    // Assert
    assertNotNull(room);
  }

  /**
   * Test the addRoom method of the RoomService class with an invalid houseID.
   */
  @Test
  void shouldThrowException_whenHouseIDIsInvalid() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    RoomRepository roomRepository = mock(RoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    HouseRepository houseRepository = mock(HouseRepository.class);
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);
    when(houseRepository.ofIdentity(any(HouseID.class))).thenReturn(Optional.empty());

    // Act+Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> roomServiceImpl.addRoom(houseID, roomName, dimension, roomFloor));
    assertEquals("House with ID " + houseID + " not found.", exception.getMessage());
  }

  /**
   * Test the getRooms method of the RoomService class.
   */
  @Test
  void shouldReturnAllRooms_whenGetRoomsIsCalled() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    RoomRepository roomRepository = mock(RoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    HouseRepository houseRepository = mock(HouseRepository.class);
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    Room mockRoom = mock(Room.class);
    when(roomRepository.findAll()).thenReturn(List.of(mockRoom));

    int expected = 1;

    // Act
    List<Room> rooms = roomServiceImpl.getAllRooms();

    // Assert
    assertEquals(expected, rooms.size());
  }

  /**
   * Test the getRooms method of the RoomService class when there are no rooms.
   */
  @Test
  void shouldReturnEmptyList_whenGetRoomsIsCalledAndThereAreNoRooms() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    RoomRepository roomRepository = mock(RoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    HouseRepository houseRepository = mock(HouseRepository.class);
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    when(roomRepository.findAll()).thenReturn(List.of());

    int expected = 0;

    // Act
    List<Room> rooms = roomServiceImpl.getAllRooms();

    // Assert
    assertEquals(expected, rooms.size());
  }

  /**
   * Test the getRooms method of the RoomService class when there are multiple rooms.
   */
  @Test
  void shouldReturnAllRooms_whenGetRoomsIsCalledAndThereAreMultipleRooms() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    RoomRepository roomRepository = mock(RoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    HouseRepository houseRepository = mock(HouseRepository.class);
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    Room mockRoom1 = mock(Room.class);
    Room mockRoom2 = mock(Room.class);
    when(roomRepository.findAll()).thenReturn(List.of(mockRoom1, mockRoom2));

    int expected = 2;

    // Act
    List<Room> rooms = roomServiceImpl.getAllRooms();

    // Assert
    assertEquals(expected, rooms.size());
  }

  /**
   * Test the getRoomById method of the RoomService class with a valid roomID.
   */
  @Test
  void shouldReturnRoom_whenGetRoomByIdIsCalledWithValidRoomID() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    RoomRepository roomRepository = mock(RoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    HouseRepository houseRepository = mock(HouseRepository.class);
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    RoomID roomID = mock(RoomID.class);
    Room mockRoom = mock(Room.class);
    when(roomRepository.ofIdentity(any(RoomID.class))).thenReturn(Optional.of(mockRoom));

    // Act
    Optional<Room> room = roomServiceImpl.getRoomById(roomID);

    // Assert
    assertEquals(mockRoom, room.get());
  }

  /**
   * Test the getRoomById method of the RoomService class with an invalid roomID.
   */
  @Test
  void shouldReturnEmptyOptional_whenGetRoomByIdIsCalledWithInvalidRoomID() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    RoomRepository roomRepository = mock(RoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    HouseRepository houseRepository = mock(HouseRepository.class);
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    RoomID roomID = mock(RoomID.class);
    when(roomRepository.ofIdentity(any(RoomID.class))).thenReturn(Optional.empty());

    // Act
    Optional<Room> room = roomServiceImpl.getRoomById(roomID);

    // Assert
    assertTrue(room.isEmpty());
  }

  /**
   * Should throw an exception when the roomRepository is null.
   */
  @Test
  void shouldThrowException_whenRoomRepositoryIsNull() {
    // Arrange
    RoomRepository roomRepository = null;
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    HouseRepository houseRepository = mock(HouseRepository.class);

    // Act+Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new RoomServiceImpl(roomRepository, roomFactory, houseRepository));
    assertEquals("Room repository is required", exception.getMessage());
  }

  /**
   * Should throw an exception when the roomFactory is null.
   */
  @Test
  void shouldThrowException_whenRoomFactoryIsNull() {
    // Arrange
    RoomRepository roomRepository = mock(RoomRepository.class);
    IRoomFactory roomFactory = null;
    HouseRepository houseRepository = mock(HouseRepository.class);

    // Act+Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new RoomServiceImpl(roomRepository, roomFactory, houseRepository));
    assertEquals("Room factory is required", exception.getMessage());
  }

  /**
   * Should throw an exception when the houseRepository is null.
   */
  @Test
  void shouldThrowException_whenHouseRepositoryIsNull() {
    // Arrange
    RoomRepository roomRepository = mock(RoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    HouseRepository houseRepository = null;

    // Act+Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new RoomServiceImpl(roomRepository, roomFactory, houseRepository));
    assertEquals("House repository is required", exception.getMessage());
  }
}