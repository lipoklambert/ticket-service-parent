package com.epam.training.ticketservice.core.screening;

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
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String movieTitle;
    private String roomName;
    private LocalDateTime screeningDate;

    public Screening(String movieTitle, String roomName, LocalDateTime screeningDateTime) {
        this.movieTitle = movieTitle;
        this.roomName = roomName;
        this.screeningDate = screeningDateTime;
    }

}
