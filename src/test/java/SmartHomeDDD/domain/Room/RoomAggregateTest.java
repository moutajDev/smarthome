package SmartHomeDDD.domain.Room;

import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

class RoomAggregateTest {

    /**
     * Test that the Room class can be instantiated with valid parameters.
     */
    @Test
    void shouldInstantiateRoom_WhenConstructorIsCalledWithValidParameters() {
        //Arrange
        String houseIDName = "HouseID";
        String name = "RoomName";
        int width = 13;
        int length = 12;
        int height = 16;
        int floor = 1;

        HouseID houseID = new HouseID(houseIDName);
        RoomName roomName = new RoomName(name);
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);

        //Act
        Room room = new Room(houseID, roomName, dimension, roomFloor);

        //Assert
        assertNotNull(room);
    }

    /**
     * Test that the Room class throws an IllegalArgumentException when the constructor is called with a null HouseID.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullHouseID() {
        //Arrange
        String name = "RoomName";
        int width = 13;
        int length = 12;
        int height = 16;
        int floor = 1;

        HouseID houseID = null;
        RoomName roomName = new RoomName(name);
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);

        String expectedMessage = "HouseID is required";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Room(houseID, roomName, dimension, roomFloor));

        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test that the Room class throws an IllegalArgumentException when the constructor is called with a null RoomName.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullRoomName() {
        //Arrange
        String houseIDName = "HouseID";
        int width = 13;
        int length = 12;
        int height = 16;
        int floor = 1;

        HouseID houseID = new HouseID(houseIDName);
        RoomName roomName = null;
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);

        String expectedMessage = "RoomName is required";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Room(houseID, roomName, dimension, roomFloor));

        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test that the Room class throws an IllegalArgumentException when the constructor is called with a null Dimension.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullDimension() {
        //Arrange
        String houseIDName = "HouseID";
        String name = "RoomName";
        int floor = 1;

        HouseID houseID = new HouseID(houseIDName);
        RoomName roomName = new RoomName(name);
        Dimension dimension = null;
        RoomFloor roomFloor = new RoomFloor(floor);

        String expectedMessage = "Dimension is required";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Room(houseID, roomName, dimension, roomFloor));

        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));

    }

    /**
     * Test that the Room class throws an IllegalArgumentException when the constructor is called with a null RoomFloor.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullRoomFloor() {
        //Arrange
        String houseIDName = "HouseID";
        String name = "RoomName";
        int width = 13;
        int length = 12;
        int height = 16;

        HouseID houseID = new HouseID(houseIDName);
        RoomName roomName = new RoomName(name);
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = null;

        String expectedMessage = "RoomFloor is required";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Room(houseID, roomName, dimension, roomFloor));

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the houseID is returned correctly.
     */
    @Test
    void shouldReturnHouseID() {
        //Arrange
        String houseIDName = "HouseID";
        String name = "RoomName";
        int width = 13;
        int length = 12;
        int height = 16;
        int floor = 1;

        HouseID houseID = new HouseID(houseIDName);
        RoomName roomName = new RoomName(name);
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);

        Room room = new Room(houseID, roomName, dimension, roomFloor);

        //Act
        HouseID result = room.getHouseID();

        //Assert
        assertTrue(room.toString().contains(result.toString()));
    }

    /**
     * Tests if the room name is returned correctly.
     */
    @Test
    void shouldReturnRoomName() {
        //Arrange
        String houseIDName = "HouseID";
        String name = "RoomName";
        int width = 13;
        int length = 12;
        int height = 16;
        int floor = 1;

        HouseID houseID = new HouseID(houseIDName);
        RoomName roomName = new RoomName(name);
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);

        Room room = new Room(houseID, roomName, dimension, roomFloor);

        //Act
        RoomName result = room.getRoomName();

        //Assert
        assertEquals(name, result.toString());
    }

    /**
     * Tests if the room dimension is returned correctly.
     */
    @Test
    void shouldReturnDimension() {
        //Arrange
        String houseIDName = "HouseID";
        String name = "RoomName";
        int width = 13;
        int length = 12;
        int height = 16;
        int floor = 1;

        HouseID houseID = new HouseID(houseIDName);
        RoomName roomName = new RoomName(name);
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);

        Room room = new Room(houseID, roomName, dimension, roomFloor);

        //Act
        Dimension result = room.getDimension();

        //Assert
        assertEquals(dimension, result);
    }

    /**
     * Tests if the room ID is returned correctly.
     */
    @Test
    void shouldReturnRoomFloor() {
        //Arrange
        String houseIDName = "HouseID";
        String name = "RoomName";
        int width = 13;
        int length = 12;
        int height = 16;
        int floor = 1;

        HouseID houseID = new HouseID(houseIDName);
        RoomName roomName = new RoomName(name);
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);

        Room room = new Room(houseID, roomName, dimension, roomFloor);

        //Act
        RoomFloor result = room.getRoomFloor();

        //Assert
        assertEquals(roomFloor, result);
    }

    /**
     * Tests if the equals method returns false when comparing a room to a null object.
     */
    @Test
    void shouldReturnFalse_WhenRoomIsComparedToNull() {
        //Arrange
        String houseIDName = "HouseID";
        String name = "RoomName";
        int width = 13;
        int length = 12;
        int height = 16;
        int floor = 1;

        HouseID houseID = new HouseID(houseIDName);
        RoomName roomName = new RoomName(name);
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);

        Room room = new Room(houseID, roomName, dimension, roomFloor);

        //Act
        boolean result = room.equals(null);

        //Assert
        assertFalse(result);
    }

    /**
     * Tests if the equals method returns true when comparing the same room.
     */
    @Test
    void shouldReturnTrue_WhenTwoRoomsHaveTheSameID() {
        //Arrange
        String houseIDName = "HouseID";
        String name = "RoomName";
        int width = 13;
        int length = 12;
        int height = 16;
        int floor = 1;

        HouseID houseID = new HouseID(houseIDName);
        RoomName roomName = new RoomName(name);
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);

        try (MockedConstruction<RoomID> mockedRoom = mockConstruction(RoomID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("RoomID");
        })) {
            Room room1 = new Room(houseID, roomName, dimension, roomFloor);
            Room room2 = new Room(houseID, roomName, dimension, roomFloor);

            //Act
            boolean result = room1.equals(room2);

            //Assert
            assertTrue(result);
        }
    }

    /**
     * Tests if the equals method returns false when comparing two different rooms.
     */
    @Test
    void shouldReturnFalse_WhenThereAreTwoDifferentHouses() {
        //Arrange
        String houseIDName1 = "HouseID";
        String houseIDName2 = "HouseID2";
        String name = "RoomName";
        int width = 13;
        int length = 12;
        int height = 16;
        int floor = 1;

        HouseID houseID = new HouseID(houseIDName1);
        HouseID houseID2 = new HouseID(houseIDName2);
        RoomName roomName = new RoomName(name);
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);

        Room room1 = new Room(houseID, roomName, dimension, roomFloor);
        Room room2 = new Room(houseID2, roomName, dimension, roomFloor);

        //Act
        boolean result = room1.equals(room2);

        //Assert
        assertFalse(result);
    }


    /**
     * Tests if the getID method returns the correct room ID.
     */
    @Test
    void shouldReturnRoomID() {
        //Arrange
        String houseIDName = "HouseID";
        String name = "RoomName";
        int width = 13;
        int length = 12;
        int height = 16;
        int floor = 1;

        HouseID houseID = new HouseID(houseIDName);
        RoomName roomName = new RoomName(name);
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);

        Room room = new Room(houseID, roomName, dimension, roomFloor);
        //Act
        RoomID result = room.getID();

        //Assert
        assertNotNull(result);
    }

    /**
     * Tests if the equals method returns true when comparing the same room.
     */
    @Test
    void shouldReturnTrue_WhenObjectIsComparedToItself() {
        //Arrange
        String houseIDName = "HouseID";
        String name = "RoomName";
        int width = 13;
        int length = 12;
        int height = 16;
        int floor = 1;

        HouseID houseID = new HouseID(houseIDName);
        RoomName roomName = new RoomName(name);
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);

        Room room = new Room(houseID, roomName, dimension, roomFloor);

        //Act
        boolean result = room.equals(room);

        //Assert
        assertTrue(result);
    }

    /**
     * Verifica se o método {@code hashCode} é gerado a partir do {@code RoomID}
     * em linha com a implementação sobrescrita do método {@code equals}.
     */
    @Test
    void hashCodeShouldBeGenerateFromRoomIDImLineWithOverritenEquals(){
        String houseIDName = "HouseID";
        String name = "RoomName";
        int width = 13;
        int length = 12;
        int height = 16;
        int floor = 1;

        HouseID houseID = new HouseID(houseIDName);
        RoomName roomName = new RoomName(name);
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);

        Room room = new Room(houseID, roomName, dimension, roomFloor);
        RoomID roomID = room.getID();
        int expectedHashCode = roomID.hashCode();

        //Act
        int hashCode = room.hashCode();

        //Assert
        assertEquals(expectedHashCode, hashCode);

    }
}