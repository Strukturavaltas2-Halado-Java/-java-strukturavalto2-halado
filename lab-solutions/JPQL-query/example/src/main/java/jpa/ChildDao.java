package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ChildDao {

    private EntityManagerFactory factory;

    public ChildDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public List<Child> findChildrenQuery1(int year) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager
                    .createQuery("", Child.class)
                    .setParameter("year", year)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    public Child findChildQuery4(String name, int year) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager
                    .createQuery("", Child.class)
                    .setParameter("name", name)
                    .setParameter("year", year)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }

    public List<Child> findChildrenQuery7() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager
                    .createQuery("", Child.class)
                    .getResultList();
        } finally {
            manager.close();
        }
    }
}
