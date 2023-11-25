package com.epam.training.ticketservice.core.screening;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningServiceImplementation implements ScreeningService {

    private final ScreeningRepo screeningRepo;

    @Autowired
    public ScreeningServiceImplementation(ScreeningRepo screeningRepo) {
        this.screeningRepo = screeningRepo;
    }

    @Override
    public void addScreening(ScreeningDTO screeningDTO) {
        Screening screening = new Screening(screeningDTO.getMovie().getTitle(), screeningDTO.getRoom().getName(),
                screeningDTO.getScreeningDate());

        screeningRepo.save(screening);
    }


    @Override
    public void deleteScreening(String movieTitle, String roomName, LocalDateTime screeningDateTime) {
        screeningRepo.deleteByMovieTitleAndRoomNameAndScreeningDate(movieTitle, roomName, screeningDateTime);
    }

    @Override
    public List<Screening> listScreenings() {
        return screeningRepo.findAll();
    }

    @Override
    public Long getScreeningId(String movieTitle, String roomName, LocalDateTime screeningDateTime) {
        Optional<Screening> wantedProjection =
                screeningRepo.findByMovieTitleAndRoomNameAndScreeningDate(movieTitle, roomName, screeningDateTime);

        if (wantedProjection.isPresent()) {
            return wantedProjection.get().getId();
        }
        return -1L;
    }

    @Override
    public String getRoomNameById(Long id) {
        Screening wantedScreening = screeningRepo.getById(id);
        return wantedScreening.getRoomName();
    }

    @Override
    public Screening getScreeningById(Long id) {
        return screeningRepo.getById(id);
    }

    @Override
    public Optional<Screening> getScreeningByTitleRoomAndDateTime(String movieTitle, String roomName, LocalDateTime screeningDateTime) {
        return screeningRepo.findByMovieTitleAndRoomNameAndScreeningDate(movieTitle, roomName, screeningDateTime);
    }

    @Override
    public List<Screening> getScreeningsInSameRoom(String roomName) {
        return screeningRepo.findByRoomName(roomName);
    }
}
