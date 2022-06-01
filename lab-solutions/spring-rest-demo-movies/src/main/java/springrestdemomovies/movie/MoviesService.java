package springrestdemomovies.movie;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MoviesService {

    private AtomicLong idGenerator = new AtomicLong();
    private List<Movie> movies = new ArrayList<>();

    private ModelMapper modelMapper;

    public MoviesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> getAllMovies() {
        return movies.stream()
                .map(m->modelMapper.map(m, MovieDto.class))
                .collect(Collectors.toList());
    }

    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(idGenerator.incrementAndGet(),command.getTitle(),command.getLength());
        movies.add(movie);
        return modelMapper.map(movie,MovieDto.class);
    }

    public MovieDto getMovieById(long id) {
        Movie movie = movies.stream()
                        .filter(m->m.getId()==id)
                        .findFirst().orElseThrow(()->new MovieNotFoundException("Movie not found with id:"+id));

        return modelMapper.map(movie,MovieDto.class);

    }
}
