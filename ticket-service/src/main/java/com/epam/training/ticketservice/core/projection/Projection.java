package com.epam.training.ticketservice.core.projection;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Generated
public class Projection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String movieTitle;
    private String roomName;
    private LocalDateTime projectionDate;

    public Projection(String movieTitle, String roomName, LocalDateTime projectionDateTime) {
        this.movieTitle = movieTitle;
        this.roomName = roomName;
        this.projectionDate = projectionDateTime;
    }

}
