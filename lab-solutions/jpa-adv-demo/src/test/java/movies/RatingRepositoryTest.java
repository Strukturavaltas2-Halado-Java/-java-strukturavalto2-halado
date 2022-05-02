package movies;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RatingRepositoryTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    RatingRepository repository = new RatingRepository(factory);
    MovieRepository movieRepository = new MovieRepository(factory);


    @Test
    void testSaveAndReadById(){
        Rating rating = new Rating(3.4,"user1");
        Rating rating2 = new Rating(4.5,"sda");
        Movie movie = new Movie("Titanic", LocalDate.of(1994,11,11),121);
        movieRepository.saveMovie(movie);
        rating.setMovie(movie);
        rating2.setMovie(movie);
        repository.saveRating(rating);
        repository.saveRating(rating2);



        Rating other = repository.findById(rating.getId());

        System.out.println(other.getId());
    }

}