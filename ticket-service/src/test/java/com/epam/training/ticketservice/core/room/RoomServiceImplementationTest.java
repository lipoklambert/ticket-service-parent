package com.epam.training.ticketservice.core.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RoomServiceImplementationTest {

    @Mock
    private RoomRepo roomRepo;

    @InjectMocks
    private RoomServiceImplementation roomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRoom_ShouldSaveRoom() {
        RoomDTO roomDTO = new RoomDTO("Room1", 5L, 5L);

        roomService.createRoom(roomDTO);

        // Verify that save method is called with the correct Room object
        verify(roomRepo, times(1)).save(argThat(room -> "Room1".equals(room.getName())
                && room.getSeatColumns() == 5L
                && room.getSeatRows() == 5L));
    }

    @Test
    void updateRoom_WithExistingRoom_ShouldUpdateRoom() {
        RoomDTO roomDTO = new RoomDTO("Room1", 7L, 7L);
        Room existingRoom = new Room("Room1", 5L, 5L);

        when(roomRepo.findByName("Room1")).thenReturn(Optional.of(existingRoom));

        roomService.updateRoom(roomDTO);

        // Verify that save method is called with the updated Room object
        verify(roomRepo, times(1)).save(argThat(room -> "Room1".equals(room.getName())
                && room.getSeatColumns() == 7L
                && room.getSeatRows() == 7L));
    }

    @Test
    void updateRoom_WithNonExistingRoom_ShouldNotUpdateRoom() {
        RoomDTO roomDTO = new RoomDTO("NonExistingRoom", 7L, 7L);

        when(roomRepo.findByName("NonExistingRoom")).thenReturn(Optional.empty());

        roomService.updateRoom(roomDTO);

        // Verify that save method is not called
        verify(roomRepo, never()).save(any());
    }

    @Test
    void deleteRoom_ShouldDeleteRoom() {
        String roomName = "Room1";

        roomService.deleteRoom(roomName);

        // Verify that deleteByName method is called with the correct room name
        verify(roomRepo, times(1)).deleteByName(roomName);
    }

    @Test
    void listRooms_ShouldReturnListOfRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Room1", 5L, 5L));
        rooms.add(new Room("Room2", 10L, 10L));

        when(roomRepo.findAll()).thenReturn(rooms);

        List<Room> result = roomService.listRooms();

        // Verify that findAll method is called
        verify(roomRepo, times(1)).findAll();

        // Verify that the result matches the expected list of rooms
        assertEquals(rooms, result);
    }

    @Test
    void getRoomByName_WithExistingRoom_ShouldReturnRoom() {
        String roomName = "Room1";
        Room existingRoom = new Room(roomName, 5L, 5L);

        when(roomRepo.findByName(roomName)).thenReturn(Optional.of(existingRoom));

        Optional<Room> result = roomService.getRoomByName(roomName);

        // Verify that findByName method is called with the correct room name
        verify(roomRepo, times(1)).findByName(roomName);

        // Verify that the result contains the existing room
        assertEquals(Optional.of(existingRoom), result);
    }

    @Test
    void getRoomByName_WithNonExistingRoom_ShouldReturnEmptyOptional() {
        String roomName = "NonExistingRoom";

        when(roomRepo.findByName(roomName)).thenReturn(Optional.empty());

        Optional<Room> result = roomService.getRoomByName(roomName);

        // Verify that findByName method is called with the correct room name
        verify(roomRepo, times(1)).findByName(roomName);

        // Verify that the result is an empty optional
        assertEquals(Optional.empty(), result);
    }
}

