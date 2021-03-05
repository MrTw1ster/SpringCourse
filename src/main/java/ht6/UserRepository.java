package ht6;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class UserRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public UserRepository(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public void create(User user) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    public User readById(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        }
    }

    public void update(String newName, int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            user.setName(newName);
            session.getTransaction().commit();
        }
    }

    public void delete(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    public List<Product> getUserProducts(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            List<Product> products = user.getProducts();
            Hibernate.initialize(products);
            session.getTransaction().commit();
            return products;
        }
    }

    public List<UserProducts> getUserProductsWithCurrentCost(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            List<UserProducts> products = user.getUserProducts();
            Hibernate.initialize(products);
            session.getTransaction().commit();
            return products;
        }
    }
}
