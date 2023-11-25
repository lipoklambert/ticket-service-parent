package com.epam.training.ticketservice.core.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    @Modifying
    @Transactional
    List<Room> deleteByName(String name);

    Optional<Room> findByName(String name);

}