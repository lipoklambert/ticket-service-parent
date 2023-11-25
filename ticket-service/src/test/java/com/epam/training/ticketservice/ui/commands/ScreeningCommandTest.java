package com.epam.training.ticketservice.ui.commands;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ScreeningCommandTest {

    @Mock
    private ScreeningService screeningService;

    @Mock
    private MovieService movieService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private ScreeningCommand screeningCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateScreeningFailure() {
        // Arrange
        String movieTitle = "MovieTitle";
        String roomName = "RoomName";
        String screeningDate = "2023-01-01 12:00";

        when(movieService.getExistingMovieByTitle(movieTitle)).thenReturn(Optional.empty());
        when(roomService.getRoomByName(roomName)).thenReturn(Optional.empty());

        String result = screeningCommand.createScreening(movieTitle, roomName, screeningDate);

        verify(screeningService, never()).addScreening(any());
        assertEquals("Saving unsuccessful!", result);
    }

    @Test
    public void testCreateScreeningSuccess() {
        // Arrange
        String movieTitle = "MovieTitle";
        String roomName = "RoomName";
        String screeningDate = "2023-01-01 12:00";

        Movie mockMovie = new Movie("MovieTitle", "Genre", 120L);
        Room mockRoom = new Room("RoomName", 10L, 10L);

        when(movieService.getExistingMovieByTitle(movieTitle)).thenReturn(Optional.of(mockMovie));
        when(roomService.getRoomByName(roomName)).thenReturn(Optional.of(mockRoom));
        when(screeningService.getScreeningsInSameRoom(roomName)).thenReturn(Collections.emptyList());

        // Act
        String result = screeningCommand.createScreening(movieTitle, roomName, screeningDate);

        // Assert
        verify(screeningService, times(1)).addScreening(any());
        assertEquals("Screening saved.", result);
    }
}
