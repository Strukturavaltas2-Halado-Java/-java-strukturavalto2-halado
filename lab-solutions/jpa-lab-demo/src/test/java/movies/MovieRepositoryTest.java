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

    MovieRepository repository;
    EntityManagerFactory emf;

    @BeforeEach
    void init(){
        emf = Persistence.createEntityManagerFactory("pu");

        repository = new MovieRepository(emf);
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





}