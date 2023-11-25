package com.epam.training.ticketservice.ui.commands;

import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.screening.Screening;
import com.epam.training.ticketservice.core.screening.ScreeningDto;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class ScreeningCommand {

    private final ScreeningService screeningService;
    private final MovieService movieService;
    private final RoomService roomService;

    @Autowired
    public ScreeningCommand(ScreeningService screeningService, MovieService movieService,
                            RoomService roomService) {
        this.screeningService = screeningService;
        this.roomService = roomService;
        this.movieService = movieService;
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "create screening", value = "Creates a new screening.")
    public String createScreening(String movieTitle, String roomName, String screeningDate) {
        Optional<Movie> movie = movieService.getExistingMovieByTitle(movieTitle);
        Optional<Room> room = roomService.getRoomByName(roomName);

        if (room.isPresent() && movie.isPresent()) {
            LocalDateTime screeningTime = LocalDateTime.parse(screeningDate,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            List<Screening> screeningsInSameRoom = screeningService.getScreeningsInSameRoom(roomName);

            for (Screening screening : screeningsInSameRoom) {
                LocalDateTime existingScreeningStart = screening.getScreeningDate();
                LocalDateTime existingScreeningEnd = existingScreeningStart
                        .plusMinutes(movieService.getLengthInMinutes(screening.getMovieTitle()));

                if (screeningTime.isAfter(existingScreeningStart) 
                        && screeningTime.isBefore(existingScreeningEnd)) {
                    return "There is an overlapping screening";
                }

                LocalDateTime breakStart = existingScreeningEnd;
                LocalDateTime breakEnd = breakStart.plusMinutes(10);

                if (screeningTime.isAfter(breakStart) && screeningTime.isBefore(breakEnd)) {
                    return "This would start in the break period after another screening in this room";
                }
            }

            ScreeningDto screeningDto = new ScreeningDto(movie.get(), room.get(), screeningTime);
            screeningService.addScreening(screeningDto);

            return "Screening saved.";
        }
        return "Saving unsuccessful!";
    }

    @ShellMethod(key = "list screenings", value = "Lists all screenings.")
    public String listAllScreenings() {
        List<Screening> screenings = screeningService.listScreenings();
        String stringToReturn = "";
        if (screenings.isEmpty()) {
            return "There are no screenings";
        } else {
            for (Screening screening : screenings) {
                Optional<Movie> movie = movieService.getExistingMovieByTitle(screening.getMovieTitle());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                String formattedDateTime = screening.getScreeningDate().format(formatter);
                stringToReturn +=
                        screening.getMovieTitle() + " (" + movie.get().getGenre() + ", " + movie.get().getLength()
                                + " minutes), screened in room " + screening.getRoomName()
                                + ", at " + formattedDateTime + "\n";
            }
        }
        return stringToReturn;
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "delete screening", value = "Deletes a screening.")
    public String deleteScreening(String movieTitle, String roomName, String screeningDate) {
        LocalDateTime screeningTime =
                LocalDateTime.parse(screeningDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        screeningService.deleteScreening(movieTitle, roomName, screeningTime);

        return "Screening deleted";
    }
}
