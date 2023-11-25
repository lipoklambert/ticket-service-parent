package com.epam.training.ticketservice.core.movie;

public class MovieDto {
    private String title;
    private String genre;
    private Long length;

    public MovieDto() {
        // Default constructor
    }

    public MovieDto(String title, String genre, Long length) {
        this.title = title;
        this.genre = genre;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
