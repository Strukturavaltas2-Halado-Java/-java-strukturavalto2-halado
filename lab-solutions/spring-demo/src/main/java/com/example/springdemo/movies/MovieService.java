package com.example.springdemo.movies;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {

    private List<Movie> movies = List.of(
            new Movie("Titanic", LocalDate.of(1996,11,2)),
            new Movie("Jurassic Park", LocalDate.of(1993,11,1))
    );

    public Movie findByTitle(String title){
        return movies.stream().filter(m->m.getTitle().equals(title)).findFirst().orElse(null);
    }


}
