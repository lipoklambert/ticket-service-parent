package com.epam.training.ticketservice.ui.commands;

import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.core.room.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RoomCommandTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomCommand roomCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testDeleteRoom() {
        String name = "Room1";

        String result = roomCommand.deleteRoom(name);

        verify(roomService).deleteRoom(name);
        assertEquals("Room deleted", result);
    }

    @Test
    void testListRoomsWhenNoRoomsExist() {
        when(roomService.listRooms()).thenReturn(Collections.emptyList());

        String result = roomCommand.listRooms();

        assertEquals("There are no rooms at the moment", result.trim());
    }

    @Test
    void createNewRoom() {
        assertEquals("Room created", roomCommand.createNewRoom("testroom", 69L, 420L));
    }

    @Test
    void updateRoom() {
        assertEquals("Room updated", roomCommand.updateRoom("room", 1L, 1L));
    }

    @Test
    void deleteRoom() {
        assertEquals("Room deleted", roomCommand.deleteRoom("name"));
    }


}

