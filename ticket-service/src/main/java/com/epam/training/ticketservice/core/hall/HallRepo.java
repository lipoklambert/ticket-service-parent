package com.epam.training.ticketservice.core.hall;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface HallRepo extends JpaRepository<Hall, Long> {

    @Modifying
    @Transactional
    List<Hall> deleteByName(String name);

    Optional<Hall> findByName(String name);

}