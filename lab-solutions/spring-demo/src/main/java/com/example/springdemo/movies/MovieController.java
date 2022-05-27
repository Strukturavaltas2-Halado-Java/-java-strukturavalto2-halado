package com.example.springdemo.movies;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MovieController {

    private MovieService service;


    public Movie findByTitle(String title){
        return service.findByTitle(title);
    }

    @GetMapping("/movies")
    public List<Movie> listAllMovies(){
        return service.listAllMovies();
    }
}
