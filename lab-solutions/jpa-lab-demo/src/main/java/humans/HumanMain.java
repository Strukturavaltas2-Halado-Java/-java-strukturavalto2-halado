package humans;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

public class HumanMain {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager em1 = factory.createEntityManager();
        EntityManager em2 = factory.createEntityManager();

        Human h = new Human("John",12);

        em1.getTransaction().begin();
        em1.persist(h);
        em1.getTransaction().commit();
        em1.close();


       // Human found= em2.find(Human.class, h.getId());
        Human found2 = em2.getReference(Human.class,h.getId());
      //  System.out.println(found);
        System.out.println(found2);








    }

}
