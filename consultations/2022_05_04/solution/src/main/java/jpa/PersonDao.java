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
//                    .createQuery("select p from Person p where p.children.size > 1 order by p.name", Person.class)
                    .createQuery("select p from Person p where size(p.children) > 1 order by p.name", Person.class)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    public Person findPersonQuery3() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager
                    .createQuery("select p from Person p where p.children.size = (select max(p.children.size) from Person p)", Person.class)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }

    public Person findPersonQuery5(String name) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager
                    .createQuery("select c.person from Child c where c.name = :name", Person.class)
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
                    .createQuery("select avg(p.children.size) from Person p", Double.class)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }
}
