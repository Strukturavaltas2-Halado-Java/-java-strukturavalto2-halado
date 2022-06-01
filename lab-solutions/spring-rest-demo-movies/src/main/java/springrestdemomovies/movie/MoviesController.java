package springrestdemomovies.movie;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MoviesController {

    private MoviesService moviesService;

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return moviesService.getAllMovies();
    }

    @PostMapping
    public MovieDto createMovie(@RequestBody CreateMovieCommand command) {
        return moviesService.createMovie(command);
    }

    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable("id") long id){
        return moviesService.getMovieById(id);
    }

}
