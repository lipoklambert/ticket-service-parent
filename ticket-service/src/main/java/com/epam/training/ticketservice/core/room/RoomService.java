package com.epam.training.ticketservice.core.room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    void createRoom(RoomDto roomDto);

    void updateRoom(RoomDto roomDto);

    void deleteRoom(String title);

    List<Room> listRooms();

    Optional<Room> getRoomByName(String name);

}
