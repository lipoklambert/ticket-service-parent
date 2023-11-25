package com.epam.training.ticketservice.core.screening;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScreeningService {

    void addScreening(String movieTitle, String roomName, LocalDateTime screeningDateTime);

    void deleteScreening(String movieTitle, String roomName, LocalDateTime screeningDateTime);

    List<Screening> listScreenings();

    Long getScreeningId(String movieTitle, String roomName, LocalDateTime screeningDateTime);

    String getRoomNameById(Long id);

    Screening getScreeningById(Long id);

    Optional<Screening> getScreeningByTitleRoomAndDateTime(String movieTitle, String roomName, LocalDateTime screeningDateTime);

    List<Screening> getScreeningsInSameRoom(String roomName);
}
