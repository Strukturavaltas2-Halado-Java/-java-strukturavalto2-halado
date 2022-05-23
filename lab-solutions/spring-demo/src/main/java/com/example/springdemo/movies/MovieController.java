package com.example.springdemo.movies;


import org.springframework.stereotype.Controller;

@Controller
public class MovieController {

    private MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    public Movie findByTitle(String title){
        return service.findByTitle(title);
    }
}
