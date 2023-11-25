package com.epam.training.ticketservice.ui.commands;

import com.epam.training.ticketservice.core.movie.MovieDto;
import com.epam.training.ticketservice.core.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import com.epam.training.ticketservice.core.movie.Movie;
import java.util.List;

@ShellComponent
public class MovieCommand extends SecurityConfiguration {

    private final MovieService movieService;

    @Autowired
    public MovieCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @ShellMethod(key = "create movie", value = "Add the movie to the Database")
    @ShellMethodAvailability("isAdmin")
    public String addMovie(String title, String genre, Long length) {
        MovieDto movieDto = new MovieDto(title, genre, length);
        movieService.addMovie(movieDto);
        return "Movie created";
    }

    @ShellMethod(key = "update movie", value = "Update an existing movie's properties")
    @ShellMethodAvailability("isAdmin")
    public String updateMovie(String title, String genre, Long length) {
        MovieDto movieDto = new MovieDto(title, genre, length);
        movieService.updateMovie(movieDto);
        return "Movie updated";
    }

    @ShellMethod(key = "delete movie", value = "Deletes movie from database.")
    @ShellMethodAvailability("isAdmin")
    public String deleteMovie(String title) {
        movieService.deleteMovie(title);
        return "Movie deleted";
    }

    @ShellMethod(key = "list movies", value = "List all movies in the Database")
    public String listMovies() {
        List<Movie> movieList = movieService.listMovies();
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
