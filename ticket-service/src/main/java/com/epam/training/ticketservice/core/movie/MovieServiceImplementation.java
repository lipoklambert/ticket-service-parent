package com.epam.training.ticketservice.core.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImplementation implements MovieService {

    private final MovieRepo movieRepo;

    @Autowired
    public MovieServiceImplementation(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public void addMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setLength(movieDTO.getLength());
        movieRepo.save(movie);
    }

    @Override
    public void updateMovie(MovieDTO movieDTO) {
        Optional<Movie> existingMovie = movieRepo.findByTitle(movieDTO.getTitle());
        if (existingMovie.isPresent()) {
            Movie movie = existingMovie.get();
            movie.setGenre(movieDTO.getGenre());
            movie.setLength(movieDTO.getLength());
            movieRepo.save(movie);
        }
    }

    @Override
    public void deleteMovie(String title) {
        movieRepo.deleteByTitle(title);
    }

    @Override
    public List<Movie> listMovies() {
        return movieRepo.findAll();
    }

    @Override
    public Optional<Movie> getExistingMovieByTitle(String title) {
        return movieRepo.findByTitle(title);
    }

    @Override
    public long getLengthInMinutes(String title) {
        Optional<Movie> movieTemp = movieRepo.findByTitle(title);

        if (movieTemp.isPresent()) {
            Movie movie = movieTemp.get();
            return movie.getLength();
        }
        return -1L;
    }
}
