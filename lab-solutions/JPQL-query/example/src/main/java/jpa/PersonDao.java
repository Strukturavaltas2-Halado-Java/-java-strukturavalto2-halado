package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class PersonDao {

    private EntityManagerFactory factory;

    public PersonDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void savePerson(Person person) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(person);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    public List<Person> findPeopleQuery2() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager
                    .createQuery("", Person.class)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    public Person findPersonQuery3() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager
                    .createQuery("", Person.class)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }

    public Person findPersonQuery5(String name) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager
                    .createQuery("", Person.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }

    public Double findQuery6() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager
                    .createQuery("", Double.class)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }
}
