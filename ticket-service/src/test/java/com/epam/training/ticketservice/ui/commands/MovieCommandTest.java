package com.epam.training.ticketservice.ui.commands;

import com.epam.training.ticketservice.core.movie.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieCommandTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieCommand movieCommand;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddMovie() {
        // Arrange
        String title = "Test Movie";
        String genre = "Test Genre";
        Long length = 120L;

        // Act
        String result = movieCommand.addMovie(title, genre, length);

        // Assert
        verify(movieService, times(1)).addMovie(any());
        assertEquals("Movie created", result);
    }

    @Test
    public void testUpdateMovie() {
        // Arrange
        String title = "Test Movie";
        String genre = "Test Genre";
        Long length = 120L;

        // Act
        String result = movieCommand.updateMovie(title, genre, length);

        // Assert
        verify(movieService, times(1)).updateMovie(any());
        assertEquals("Movie updated", result);
    }

    @Test
    public void testDeleteMovie() {
        // Arrange
        String title = "Test Movie";

        // Act
        String result = movieCommand.deleteMovie(title);

        // Assert
        verify(movieService, times(1)).deleteMovie(title);
        assertEquals("Movie deleted", result);
    }

    @Test
    public void testListMoviesWhenEmpty() {
        // Arrange
        when(movieService.listMovies()).thenReturn(Arrays.asList());

        // Act
        String result = movieCommand.listMovies();

        // Assert
        assertEquals("There are no movies at the moment", result.trim());
    }

}

