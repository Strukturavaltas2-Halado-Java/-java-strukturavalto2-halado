package com.example.springdemo.movies;

import java.time.LocalDate;

public class Movie {

    private String title;
    private LocalDate releaseDate;

    public Movie(String title, LocalDate releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

}
