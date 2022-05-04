package movies;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RatingServiceIT {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
    RatingRepository ratingRepository = new RatingRepository(factory);
    MovieRepository movieRepository = new MovieRepository(factory);
    RatingService ratingService = new RatingService(ratingRepository);


    @Test
    void testSaveNewRating(){
        Movie movie = new Movie("Titaniv", LocalDate.of(1994,11,12),123);

        movieRepository.saveMovie(movie);

        ratingService.saveNewRating(movie,new Rating(4.5,"user1"));
        ratingService.saveNewRating(movie,new Rating(4.7,"user1"));
    }


}