package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ScreeningServiceImplementationTest {

    @Mock
    private ScreeningRepo screeningRepo;

    @InjectMocks
    private ScreeningServiceImplementation screeningService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testDeleteScreening() {
        String movieTitle = "Movie";
        String roomName = "Room";
        LocalDateTime screeningDateTime = LocalDateTime.now();

        screeningService.deleteScreening(movieTitle, roomName, screeningDateTime);

        // Verify that the deleteByMovieTitleAndRoomNameAndScreeningDate method of screeningRepo was called
        verify(screeningRepo, times(1)).deleteByMovieTitleAndRoomNameAndScreeningDate(movieTitle, roomName, screeningDateTime);
    }

    @Test
    void testListScreenings() {
        // Mock the behavior of the findAll method
        List<Screening> screenings = new ArrayList<>();
        when(screeningRepo.findAll()).thenReturn(screenings);

        List<Screening> result = screeningService.listScreenings();

        // Verify that the findAll method of screeningRepo was called
        verify(screeningRepo, times(1)).findAll();
        // Verify that the result matches the mocked screenings
        assertEquals(screenings, result);
    }

    @Test
    void testAddScreening() {
        ScreeningDto screeningDTO = new ScreeningDto(new Movie("Movie Title"), new Room("Room Name"), LocalDateTime.now());

        screeningService.addScreening(screeningDTO);

        // Verify that the save method of screeningRepo was called with the correct arguments
        verify(screeningRepo, times(1)).save(any(Screening.class));
    }

    @Test
    void testGetScreeningIdExisting() {
        String movieTitle = "Movie";
        String roomName = "Room";
        LocalDateTime screeningDateTime = LocalDateTime.now();

        Screening screening = new Screening(movieTitle, roomName, screeningDateTime);
        when(screeningRepo.findByMovieTitleAndRoomNameAndScreeningDate(movieTitle, roomName, screeningDateTime))
                .thenReturn(Optional.of(screening));

        Long result = screeningService.getScreeningId(movieTitle, roomName, screeningDateTime);

        // Verify that the findByMovieTitleAndRoomNameAndScreeningDate method of screeningRepo was called
        verify(screeningRepo, times(1)).findByMovieTitleAndRoomNameAndScreeningDate(movieTitle, roomName, screeningDateTime);
        // Verify that the result matches the ID of the mocked screening
        assertEquals(screening.getId(), result);
    }

    @Test
    void testGetScreeningIdNotExisting() {
        String movieTitle = "Nonexistent Movie";
        String roomName = "Nonexistent Room";
        LocalDateTime screeningDateTime = LocalDateTime.now();

        when(screeningRepo.findByMovieTitleAndRoomNameAndScreeningDate(movieTitle, roomName, screeningDateTime))
                .thenReturn(Optional.empty());

        Long result = screeningService.getScreeningId(movieTitle, roomName, screeningDateTime);

        // Verify that the findByMovieTitleAndRoomNameAndScreeningDate method of screeningRepo was called
        verify(screeningRepo, times(1)).findByMovieTitleAndRoomNameAndScreeningDate(movieTitle, roomName, screeningDateTime);
        // Verify that the result is -1L for non-existent screenings
        assertEquals(-1L, result);
    }

}

