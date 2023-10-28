package com.epam.training.ticketservice.ui.commands;

import com.epam.training.ticketservice.core.hall.Hall;
import com.epam.training.ticketservice.core.hall.HallDataHandling;
import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.movie.MovieDataHandling;
import com.epam.training.ticketservice.core.projection.Projection;
import com.epam.training.ticketservice.core.projection.ProjectionDataHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class ProjectionCommand {

    private final ProjectionDataHandling projectionDataHandling;
    private final MovieDataHandling movieDataHandling;
    private final HallDataHandling hallDataHandling;

    @Autowired
    public ProjectionCommand(ProjectionDataHandling projectionDataHandling, MovieDataHandling movieDataHandling, HallDataHandling hallDataHandling) {
        this.projectionDataHandling = projectionDataHandling;
        this.hallDataHandling = hallDataHandling;
        this.movieDataHandling = movieDataHandling;
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "create screening", value = "Creates a new screening.")
    public String createScreening(String movieTitle, String hallName, String projectionDate) {
        Optional<Movie> movie = movieDataHandling.getExistingMovieByTitle(movieTitle);
        Optional<Hall> hall = hallDataHandling.getCinemaHallByName(hallName);

        if (hall.isPresent() && movie.isPresent()) {
            LocalDateTime projectionTime = LocalDateTime.parse(projectionDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            List<Projection> projectionsInSameRoom = projectionDataHandling.getProjectionsInSameRoom(hallName);

            for (Projection projection : projectionsInSameRoom) {
                LocalDateTime existingProjectionStart = projection.getProjectionDate();
                LocalDateTime existingProjectionEnd = existingProjectionStart
                        .plusMinutes(movieDataHandling.getLengthInMinutes(projection.getMovieTitle()));

                if (projectionTime.isAfter(existingProjectionStart) && projectionTime.isBefore(existingProjectionEnd)) {
                    return "There is an overlapping screening";
                }

                LocalDateTime breakStart = existingProjectionEnd;
                LocalDateTime breakEnd = breakStart.plusMinutes(10);

                if (projectionTime.isAfter(breakStart) && projectionTime.isBefore(breakEnd)) {
                    return "This would start in the break period after another screening in this room";
                }
            }

            projectionDataHandling.addProjection(movieTitle, hallName, projectionTime);

            return "Screening saved.";
        }
        return "Fill out the required fields, please.";
    }

    @ShellMethod(key = "list screenings", value = "Lists all screenings.")
    public String listAllProjections() {
        List<Projection> projections = projectionDataHandling.listProjections();
        String stringToReturn = "";
        if (projections.isEmpty()) {
            return "There are no screenings";
        } else {
            for (Projection projection : projections) {
                Optional<Movie> movie = movieDataHandling.getExistingMovieByTitle(projection.getMovieTitle());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                String formattedDateTime = projection.getProjectionDate().format(formatter);
                stringToReturn +=
                        projection.getMovieTitle() + " (" + movie.get().getGenre() + ", " + movie.get().getLength()
                                + " minutes), screened in room " + projection.getRoomName() + ", at " + formattedDateTime + "\n";
            }
        }
        return stringToReturn;
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "delete screening", value = "Deletes a screening.")
    public String deleteProjection(String movieTitle, String roomName, String projectionDate) {
        LocalDateTime projectionTime =
                LocalDateTime.parse(projectionDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        projectionDataHandling.deleteProjection(movieTitle, roomName, projectionTime);

        return "If the screening existed, it was deleted";
    }
}
