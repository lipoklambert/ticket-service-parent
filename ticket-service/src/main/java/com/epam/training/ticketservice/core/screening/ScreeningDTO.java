package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;

import java.time.LocalDateTime;

public class ScreeningDTO {
    private Movie movie;
    private Room room;
    private LocalDateTime screeningDate;

    public ScreeningDTO(Movie movie, Room room, LocalDateTime screeningDate) {
        this.movie = movie;
        this.room = room;
        this.screeningDate = screeningDate;
    }


    public Movie getMovie() {
        return movie;
    }


    public Room getRoom() {
        return room;
    }


    public LocalDateTime getScreeningDate() {
        return screeningDate;
    }

}
