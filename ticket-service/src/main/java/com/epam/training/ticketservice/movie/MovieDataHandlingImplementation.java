package com.epam.training.ticketservice.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MovieDataHandlingImplementation implements MovieDataHandling {

    private final MovieRepo movieRepo;

    @Autowired
    public MovieDataHandlingImplementation(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    @Transactional
    public void addMovie(String title, String genre, Long length) {
        Movie movie = new Movie();
        movie.setGenre(genre);
        movie.setLength(length);
        movie.setTitle(title);
        movieRepo.save(movie);
    }

    @Override
    @Transactional
    public void updateMovie(String title, String genre, Long length) {
        Optional<Movie> existingMovie = movieRepo.findByTitle(title);
        if (existingMovie.isPresent()) {
            Movie movie = existingMovie.get();
            movie.setGenre(genre);
            movie.setLength(length);
            movieRepo.save(movie);
        }
    }

    @Override
    @Transactional
    public void deleteMovie(String title) {
        movieRepo.deleteByTitle(title);
    }

    @Override
    public List<Movie> listMovies() {
        return movieRepo.findAll();
    }

}