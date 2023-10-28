package com.epam.training.ticketservice.core.projection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectionDataHandlingImplementation implements ProjectionDataHandling {

    private final ProjectionRepo projectionRepo;

    @Autowired
    public ProjectionDataHandlingImplementation(ProjectionRepo projectionRepo) {
        this.projectionRepo = projectionRepo;
    }

    @Override
    public void addProjection(String movieTitle, String roomName, LocalDateTime projectionDateTime) {
        Projection projection = new Projection(movieTitle, roomName, projectionDateTime);
        projectionRepo.save(projection);
    }


    @Override
    public void deleteProjection(String movieTitle, String roomName, LocalDateTime projectionDateTime) {
        projectionRepo.deleteByMovieTitleAndRoomNameAndProjectionDate(movieTitle, roomName, projectionDateTime);
    }

    @Override
    public List<Projection> listProjections() {
        return projectionRepo.findAll();
    }

    @Override
    public Long getProjectionId(String movieTitle, String roomName, LocalDateTime projectionDateTime) {
        Optional<Projection> wantedProjection =
                projectionRepo.findByMovieTitleAndRoomNameAndProjectionDate(movieTitle, roomName, projectionDateTime);

        if (wantedProjection.isPresent()) {
            return wantedProjection.get().getId();
        }
        return -1L;
    }

    @Override
    public String getRoomNameById(Long id) {
        Projection wantedProjection = projectionRepo.getById(id);
        return wantedProjection.getRoomName();
    }

    @Override
    public Projection getProjectionById(Long id) {
        return projectionRepo.getById(id);
    }

    @Override
    public Optional<Projection> getProjectionByTitleRoomAndDateTime(String movieTitle, String roomName, LocalDateTime projectionDateTime) {
        return projectionRepo.findByMovieTitleAndRoomNameAndProjectionDate(movieTitle, roomName, projectionDateTime);
    }

    @Override
    public List<Projection> getProjectionsInSameRoom(String roomName) {
        return projectionRepo.findByRoomName(roomName);
    }
}
