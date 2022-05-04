package movies;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class RatingRepository {

    private EntityManagerFactory factory;

    public RatingRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }


    public Rating saveRating(Rating rating, String movieId){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Movie movie = em.find(Movie.class,movieId);
        rating.setMovie(movie);
        em.persist(rating);
        em.getTransaction().commit();
        em.close();
        return rating;
    }


    public Rating findById(Long id){
        EntityManager em = factory.createEntityManager();
        Rating result = em.find(Rating.class,id);
        em.close();
        return result;
    }

    public int findRatingByUsernameOnMovie(String movieId, String username){
        EntityManager em = factory.createEntityManager();
        int result = em.createQuery("select r from Rating r where r.movie.id=:movieId and r.username=:username",Rating.class)
                .setParameter("movieId",movieId)
                .setParameter("username", username)
                .getResultList().size();
        em.close();
        return result;
    }
}