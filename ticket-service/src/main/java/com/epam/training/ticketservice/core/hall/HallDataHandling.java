package com.epam.training.ticketservice.core.hall;

import java.util.List;
import java.util.Optional;

public interface HallDataHandling {

    void createCinemaHall(String name, Long seatRows, Long seatColumns);

    void updateCinemaHall(String name, Long seatRows, Long seatColumns);

    void deleteCinemaHall(String title);

    List<Hall> listCinemaHalls();
    Optional<Hall> getCinemaHallByName(String name);

}
