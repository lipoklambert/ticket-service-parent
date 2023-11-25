package com.epam.training.ticketservice.core.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class MovieServiceImplementationTest {

    @Mock
    private MovieRepo movieRepo;

    @InjectMocks
    private MovieServiceImplementation movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddMovie() {
        MovieDTO movieDTO = new MovieDTO("Test Movie", "Action", 120L);

        movieService.addMovie(movieDTO);

        verify(movieRepo, times(1)).save(any(Movie.class));
    }

    @Test
    void testUpdateMovie() {
        MovieDTO movieDTO = new MovieDTO("Test Movie", "Drama", 150L);
        Movie existingMovie = new Movie("Test Movie", "Action", 120L);

        when(movieRepo.findByTitle("Test Movie")).thenReturn(Optional.of(existingMovie));

        movieService.updateMovie(movieDTO);

        verify(movieRepo, times(1)).save(existingMovie);

        assertEquals("Drama", existingMovie.getGenre());
        assertEquals(150L, existingMovie.getLength());
    }

    @Test
    void testDeleteMovie() {
        String title = "Test Movie";

        movieService.deleteMovie(title);

        verify(movieRepo, times(1)).deleteByTitle(title);
    }

    @Test
    void testListMovies() {
        Movie movie1 = new Movie("Movie 1", "Action", 120L);
        Movie movie2 = new Movie("Movie 2", "Drama", 150L);

        when(movieRepo.findAll()).thenReturn(Arrays.asList(movie1, movie2));

        List<Movie> movies = movieService.listMovies();

        assertEquals(2, movies.size());
        assertEquals("Movie 1", movies.get(0).getTitle());
        assertEquals("Movie 2", movies.get(1).getTitle());
    }

    @Test
    void testGetExistingMovieByTitle() {
        String title = "Test Movie";
        Movie existingMovie = new Movie(title, "Action", 120L);

        when(movieRepo.findByTitle(title)).thenReturn(Optional.of(existingMovie));

        Optional<Movie> result = movieService.getExistingMovieByTitle(title);

        assertTrue(result.isPresent());
        assertEquals(existingMovie, result.get());
    }

    @Test
    void testGetLengthInMinutes() {
        String title = "Test Movie";
        Movie existingMovie = new Movie(title, "Action", 120L);

        when(movieRepo.findByTitle(title)).thenReturn(Optional.of(existingMovie));

        long length = movieService.getLengthInMinutes(title);

        assertEquals(120L, length);
    }
}
