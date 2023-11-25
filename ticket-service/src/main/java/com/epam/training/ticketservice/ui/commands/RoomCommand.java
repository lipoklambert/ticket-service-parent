package com.epam.training.ticketservice.ui.commands;

import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.core.room.RoomDto;
import com.epam.training.ticketservice.core.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;

@ShellComponent
public class RoomCommand extends SecurityConfiguration {

    private final RoomService roomService;


    @Autowired
    public RoomCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "create room", value = "Creates new room in the Database")
    public String createNewRoom(String name, Long rows, Long columns) {
        RoomDto roomDto = new RoomDto(name, rows, columns);
        roomService.createRoom(roomDto);
        return "Room created";
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "update room", value = "Updates an existing room in the Database")
    public String updateRoom(String name, Long rows, Long columns) {
        RoomDto roomDto = new RoomDto(name, rows, columns);
        roomService.updateRoom(roomDto);
        return "Room updated";
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "delete room", value = "Deletes room from Database")
    public String deleteRoom(String name) {
        roomService.deleteRoom(name);
        return "Room deleted";
    }

    @ShellMethod(key = "list rooms", value = "List all rooms.")
    public String listRooms() {
        List<Room> roomList = roomService.listRooms();
        StringBuilder stringToReturn = new StringBuilder();
        if (roomList.isEmpty()) {
            stringToReturn.append("There are no rooms at the moment");
        } else {
            for (Room room : roomList) {
                stringToReturn.append(room.toString()).append("\n");
            }
        }
        return stringToReturn.toString();
    }
}
