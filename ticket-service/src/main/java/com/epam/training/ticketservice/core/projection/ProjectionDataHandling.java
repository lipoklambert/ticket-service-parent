package com.epam.training.ticketservice.core.projection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProjectionDataHandling {

    void addProjection(String movieTitle, String roomName, LocalDateTime projectionDateTime);

    void deleteProjection(String movieTitle, String roomName, LocalDateTime projectionDateTime);

    List<Projection> listProjections();

    Long getProjectionId(String movieTitle, String roomName, LocalDateTime projectionDateTime);

    String getRoomNameById(Long id);

    Projection getProjectionById(Long id);

    Optional<Projection> getProjectionByTitleRoomAndDateTime(String movieTitle, String roomName, LocalDateTime projectionDateTime);

    List<Projection> getProjectionsInSameRoom(String roomName);
}
