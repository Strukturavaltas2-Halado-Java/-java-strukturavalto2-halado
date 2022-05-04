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
                    .createQuery("select c from Child c where c.yearOfBirth > :year", Child.class)
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
//                    .createQuery("select c from Child c join fetch c.person where c.person.name = :name and c.yearOfBirth = :year", Child.class)
                    .createQuery("select c from Child c where c.person.name = :name and c.yearOfBirth = :year", Child.class)
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
//                    .createQuery("select c from Child c where c.person = (select p from Person p where size(p.children) = (select max(size(q.children)) from Person q))", Child.class)
//                    .createQuery("select c from Child c where c.person.children.size = (select max(size(q.children)) from Person q)", Child.class)
                    .createQuery("select c from Child c where c.person.children.size = (select max(q.children.size) from Person q)", Child.class)
                    .getResultList();
        } finally {
            manager.close();
        }
    }
}
