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
    public void createRoom(RoomDto roomDto) {
        Room room = new Room();
        room.setName(roomDto.getName());
        room.setSeatColumns(roomDto.getSeatColumns());
        room.setSeatRows(roomDto.getSeatRows());
        roomRepo.save(room);
    }

    @Override
    public void updateRoom(RoomDto roomDto) {
        Optional<Room> existingHall = roomRepo.findByName(roomDto.getName());

        if (existingHall.isPresent()) {
            Room updatedRoom = existingHall.get();
            updatedRoom.setSeatColumns(roomDto.getSeatColumns());
            updatedRoom.setSeatRows(roomDto.getSeatRows());
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
