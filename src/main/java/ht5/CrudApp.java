package ht5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CrudApp {
    private static SessionFactory factory;

    public static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("configs/crud/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void shutdown() {
        factory.close();
    }

    public static void create() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = new Product("Новый продукт", 88.88);
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public static void read() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 3);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public static void update() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 2);
            product.setTitle("New prod 2");
            product.setCost(555.4);
            session.getTransaction().commit();
        }
    }

    public static void delete() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 4);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public static void main(String[] args) {
        try {
            init();
            create();
            read();
            update();
            delete();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }
}
