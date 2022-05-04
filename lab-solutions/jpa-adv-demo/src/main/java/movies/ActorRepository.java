package movies;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ActorRepository {


    private EntityManagerFactory factory;

    public ActorRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Actor saveActor(Actor actor){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(actor);
        em.getTransaction().commit();
        em.close();

        return actor;
    }

    public List<String> findActorsByName(List<String> actorNames){
        EntityManager em = factory.createEntityManager();
        List<String> result = em.createQuery("select a.name from Actor a where a.name in :actorNames",String.class)
                .setParameter("actorNames",actorNames)
                .getResultList();

        em.close();
        return result;
    }
}