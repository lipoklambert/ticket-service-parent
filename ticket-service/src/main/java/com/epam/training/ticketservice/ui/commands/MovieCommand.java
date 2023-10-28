package com.epam.training.ticketservice.ui.commands;

import com.epam.training.ticketservice.core.movie.MovieDataHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import com.epam.training.ticketservice.core.movie.Movie;
import java.util.List;

@ShellComponent
public class MovieCommand extends SecurityConfiguration {

    private final MovieDataHandling movieDataHandling;

    @Autowired
    public MovieCommand(MovieDataHandling movieDataHandling) {
        this.movieDataHandling = movieDataHandling;
    }

    @ShellMethod(key = "create movie", value = "Add the movie to the Database")
    @ShellMethodAvailability("isAdmin")
    public String addMovie(String title, String genre, Long length) {
        movieDataHandling.addMovie(title, genre, length);
        return "Movie created";
    }

    @ShellMethod(key = "update movie", value = "Update an existing movie's properties")
    @ShellMethodAvailability("isAdmin")
    public String updateMovie(String title, String genre, Long length) {
        movieDataHandling.updateMovie(title, genre, length);
        return "Movie updated";
    }

    @ShellMethod(key = "delete movie", value = "Deletes movie from database.")
    @ShellMethodAvailability("isAdmin")
    public String deleteMovie(String title) {
        movieDataHandling.deleteMovie(title);
        return "Movie deleted";
    }

    @ShellMethod(key = "list movies", value = "List all movies in the Database")
    public String listMovies() {
        List<Movie> movieList = movieDataHandling.listMovies();
        StringBuilder stringToReturn = new StringBuilder();
        if (movieList.isEmpty()) {
            stringToReturn.append("There are no movies at the moment");
        } else {
            for (Movie movie : movieList) {
                stringToReturn.append(movie.toString()).append("\n");
            }
        }
        return stringToReturn.toString();
    }

}
