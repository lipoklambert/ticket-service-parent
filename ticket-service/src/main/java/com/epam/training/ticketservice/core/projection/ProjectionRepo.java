package com.epam.training.ticketservice.core.projection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectionRepo extends JpaRepository<Projection, Long> {

    @Modifying
    @Transactional
    List<Projection> deleteByMovieTitleAndRoomNameAndProjectionDate(String movieTitle, String roomName, LocalDateTime projectionDateTime);

    Optional<Projection> findByMovieTitleAndRoomNameAndProjectionDate(String movieTitle, String roomName, LocalDateTime projectionDate);

    List<Projection> findByRoomName(String roomName);
}
