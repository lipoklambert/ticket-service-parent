package com.epam.training.ticketservice.hall;

import java.util.List;

public interface HallDataHandling {

    void createCinemaHall(String name, Long seatRows, Long seatColumns);

    void updateCinemaHall(String name, Long seatRows, Long seatColumns);

    void deleteCinemaHall(String title);

    List<Hall> listCinemaHalls();

}
