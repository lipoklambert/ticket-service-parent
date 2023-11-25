package com.epam.training.ticketservice.core.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImplementation implements RoomService {

    private final RoomRepo roomRepo;

    @Autowired
    public RoomServiceImplementation(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Override
    public void createRoom(String name, Long seatRows, Long seatColumns) {
        Room room = new Room();
        room.setName(name);
        room.setSeatColumns(seatColumns);
        room.setSeatRows(seatRows);
        roomRepo.save(room);
    }

    @Override
    public void updateRoom(String name, Long seatRows, Long seatColumns) {
        Optional<Room> existingHall = roomRepo.findByName(name);

        if (existingHall.isPresent()) {
            Room updatedRoom = existingHall.get();
            updatedRoom.setSeatColumns(seatColumns);
            updatedRoom.setSeatRows(seatRows);
            roomRepo.save(updatedRoom);
        }
    }

    @Override
    public void deleteRoom(String name) {
        roomRepo.deleteByName(name);
    }

    @Override
    public List<Room> listRooms() {
        return roomRepo.findAll();
    }

    @Override
    public Optional<Room> getRoomByName(String name) {
        return roomRepo.findByName(name);
    }

}
