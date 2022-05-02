package movies;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MovieRepositoryTest {

    ActorRepository actorRepository;
    MovieRepository repository;
    EntityManagerFactory emf;

    @BeforeEach
    void init(){
        emf = Persistence.createEntityManagerFactory("pu");

        repository = new MovieRepository(emf);
        actorRepository= new ActorRepository(emf);
    }


    @Test
    void testSaveMovie(){
        Movie movie = repository.saveMovie(new Movie("Titanic", LocalDate.of(1994,12,1),121));



        assertThat(movie.getId()).isNotEqualTo(null);

    }

    @Test
    void testFindByTitle(){
        Movie movie = repository.saveMovie(new Movie("Titanic", LocalDate.of(1994,12,1),121));
        Optional<Movie> result = repository.findByTitle("Titanic");

        assertThat(result.get().getLength()).isEqualTo(121);
    }

    @Test
    void testFindByTitleWithRatings(){
        Movie movie = new Movie("Titanic", LocalDate.of(1994,12,1),121);
        movie.addRating(new Rating(6.7,"user1"));
        movie.addRating(new Rating(6.9,"user2"));
        repository.saveMovie(movie);

        Movie result = repository.findMovieByTitleWithRatings("Titanic");

        assertThat(result.getLength()).isEqualTo(121);
        assertThat(result.getRatings()).extracting(Rating::getValue).containsExactly(6.7,6.9);

    }

    @Test
    void testFindMoviesReleasedAfter(){
        Movie movie = new Movie("Titanic", LocalDate.of(1994,12,1),121);
        Movie movie1 = new Movie("LOTR", LocalDate.of(1994,12,1),121);
        movie.addRating(new Rating(6.7,"user1"));
        movie.addRating(new Rating(6.9,"user2"));
        repository.saveMovie(movie);
        repository.saveMovie(movie1);

        List<Movie> result = repository.findMoviesReleasedAfter(LocalDate.of(1994,11,11));
        System.out.println(result.size());

    }

    @Test
    void testSaveMovieWithActors(){
        Movie movie = new Movie("Titanic", LocalDate.of(1994,12,1),121);

        Actor actor1 = new Actor("Dicaprio",46);
        Actor actor2 = new Actor("Kate",42);

        actorRepository.saveActor(actor1);
        actorRepository.saveActor(actor2);

        movie.addActor(actor1);
        movie.addActor(actor2);

        repository.saveMovie(movie);

        /*List<Movie> other = repository.findMoviesReleasedAfter(LocalDate.of(1990,1,1));
        System.out.println(other.get(0));*/





    }






}