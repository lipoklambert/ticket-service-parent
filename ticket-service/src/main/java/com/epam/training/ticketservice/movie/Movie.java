package com.epam.training.ticketservice.movie;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    private String title;
    private String genre;
    private Long length;

    @Override
    public String toString() {
        return (title + " (" + genre + ", " + length.toString() + " minutes)");
    }
}