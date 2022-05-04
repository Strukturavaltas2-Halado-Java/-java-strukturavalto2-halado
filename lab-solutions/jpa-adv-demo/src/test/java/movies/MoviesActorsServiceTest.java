package movies;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoviesActorsServiceTest {


    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
    ActorRepository actorRepository = new ActorRepository(factory);
    MovieRepository movieRepository = new MovieRepository(factory);
    MoviesActorsService moviesActorsService = new MoviesActorsService(actorRepository,movieRepository);


    @Test
    void saveNewMovieWithActors(){
        Actor actor = new Actor("John",56);
        actorRepository.saveActor(actor);
        actorRepository.saveActor(new Actor("Jane",46));

        Movie movie = new Movie("Titanic", LocalDate.of(1993,11,11),123);
        moviesActorsService.saveMovieWithActors(movie, List.of(actor,new Actor("Jack",19)));


    }

}