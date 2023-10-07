package com.epam.training.ticketservice.movie;

import java.util.List;

public interface MovieDataHandling {

    void addMovie(String title, String genre, Long length);

    void updateMovie(String title, String genre, Long length);

    void deleteMovie(String title);

    List<Movie> listMovies();

}
