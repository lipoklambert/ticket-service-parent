package com.epam.training.ticketservice.hall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class HallDataHandlingImplementation implements HallDataHandling{

    private final HallRepo hallRepo;

    @Autowired
    public HallDataHandlingImplementation(HallRepo hallRepo) {
        this.hallRepo = hallRepo;
    }

    @Override
    public void createCinemaHall(String name, Long seatRows, Long seatColumns) {
        Hall hall = new Hall();
        hall.setName(name);
        hall.setSeatColumns(seatColumns);
        hall.setSeatRows(seatRows);
        hallRepo.save(hall);
    }

    @Override
    public void updateCinemaHall(String name, Long seatRows, Long seatColumns) {
        Optional<Hall> existingHall = hallRepo.findByName(name);

        if (existingHall.isPresent()) {
            Hall updatedHall = existingHall.get();
            updatedHall.setSeatColumns(seatColumns);
            updatedHall.setSeatRows(seatRows);
            hallRepo.save(updatedHall);
        }
    }

    @Override
    public void deleteCinemaHall(String name) {
        hallRepo.deleteByName(name);
    }

    @Override
    public List<Hall> listCinemaHalls() {
        return hallRepo.findAll();
    }

}
