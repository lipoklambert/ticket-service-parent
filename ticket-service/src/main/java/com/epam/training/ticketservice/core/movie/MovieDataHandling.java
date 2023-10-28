package com.epam.training.ticketservice.core.movie;

import java.util.List;
import java.util.Optional;

public interface MovieDataHandling {

    void addMovie(String title, String genre, Long length);

    void updateMovie(String title, String genre, Long length);

    void deleteMovie(String title);

    List<Movie> listMovies();

    Optional<Movie> getExistingMovieByTitle(String title);

    long getLengthInMinutes(String title);

}