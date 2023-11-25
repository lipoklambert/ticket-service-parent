package com.epam.training.ticketservice.core.room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    void createRoom(String name, Long seatRows, Long seatColumns);

    void updateRoom(String name, Long seatRows, Long seatColumns);

    void deleteRoom(String title);

    List<Room> listRooms();
    Optional<Room> getRoomByName(String name);

}
