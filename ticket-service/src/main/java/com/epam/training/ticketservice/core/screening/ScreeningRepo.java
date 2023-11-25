package com.epam.training.ticketservice.core.screening;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScreeningRepo extends JpaRepository<Screening, Long> {

    @Modifying
    @Transactional
    List<Screening> deleteByMovieTitleAndRoomNameAndScreeningDate(String movieTitle, String roomName,
                                                                  LocalDateTime screeningDateTime);

    Optional<Screening> findByMovieTitleAndRoomNameAndScreeningDate(String movieTitle, String roomName,
                                                                    LocalDateTime screeningDate);

    List<Screening> findByRoomName(String roomName);
}
