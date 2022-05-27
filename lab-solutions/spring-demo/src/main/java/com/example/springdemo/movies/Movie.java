package com.example.springdemo.movies;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Movie {

    private String title;
    private LocalDate releaseDate;


}
