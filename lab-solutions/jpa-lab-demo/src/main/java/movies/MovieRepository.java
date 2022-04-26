package movies;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class MovieRepository {

    private EntityManagerFactory factory;

    public MovieRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Movie saveMovie(Movie movie) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();
        entityManager.close();
        return movie;
    }


    public Optional<Movie> findByTitle(String title) {
        EntityManager entityManager = factory.createEntityManager();
        Movie result = entityManager.createQuery("select m from Movie m where m.title=:title", Movie.class)
                .setParameter("title", title)
                .getSingleResult();
        entityManager.close();
        return Optional.of(result);
    }

    public Optional<Movie> findMovieByTitleWithRatings(String title){
        EntityManager entityManager = factory.createEntityManager();
        Movie result = entityManager.createQuery("select m from Movie m join fetch m.ratings r where m.title=:title", Movie.class)
                .setParameter("title", title)
                .getSingleResult();
        entityManager.close();

        return Optional.of(result);
    }
}