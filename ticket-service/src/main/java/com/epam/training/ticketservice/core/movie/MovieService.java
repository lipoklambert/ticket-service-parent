package com.epam.training.ticketservice.core.movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    void addMovie(MovieDto movieDto);

    void updateMovie(MovieDto movieDto);

    void deleteMovie(String title);

    List<Movie> listMovies();

    Optional<Movie> getExistingMovieByTitle(String title);

    long getLengthInMinutes(String title);

}
