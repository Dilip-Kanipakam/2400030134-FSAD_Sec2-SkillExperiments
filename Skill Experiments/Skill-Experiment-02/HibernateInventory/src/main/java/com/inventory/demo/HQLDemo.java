package com.inventory.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class HQLDemo {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {

            sortProductsByPriceAscending(session);
            sortProductsByPriceDescending(session);
            sortProductsByQuantityDescending(session);

            getFirstThreeProducts(session);
            getNextThreeProducts(session);

            countTotalProducts(session);
            countProductsInStock(session);

            findMinMaxPrice(session);

            filterProductsByPriceRange(session, 20, 100);

            findProductsStartingWith(session, "D");

        } finally {
            session.close();
            factory.close();
        }
    }

    // Sort price ASC
    public static void sortProductsByPriceAscending(Session session) {

        String hql = "FROM Product p ORDER BY p.price ASC";
        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> products = query.list();

        System.out.println("\nProducts Sorted by Price ASC");
        products.forEach(System.out::println);
    }

    // Sort price DESC
    public static void sortProductsByPriceDescending(Session session) {

        String hql = "FROM Product p ORDER BY p.price DESC";
        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> products = query.list();

        System.out.println("\nProducts Sorted by Price DESC");
        products.forEach(System.out::println);
    }

    // Sort quantity
    public static void sortProductsByQuantityDescending(Session session) {

        String hql = "FROM Product p ORDER BY p.quantity DESC";
        Query<Product> query = session.createQuery(hql, Product.class);

        List<Product> products = query.list();

        System.out.println("\nProducts Sorted by Quantity");
        products.forEach(p -> System.out.println(p.getName() + " - " + p.getQuantity()));
    }

    // Pagination first 3
    public static void getFirstThreeProducts(Session session) {

        Query<Product> query = session.createQuery("FROM Product", Product.class);
        query.setFirstResult(0);
        query.setMaxResults(3);

        System.out.println("\nFirst 3 Products");
        query.list().forEach(System.out::println);
    }

    // Pagination next 3
    public static void getNextThreeProducts(Session session) {

        Query<Product> query = session.createQuery("FROM Product", Product.class);
        query.setFirstResult(3);
        query.setMaxResults(3);

        System.out.println("\nNext 3 Products");
        query.list().forEach(System.out::println);
    }

    // Count products
    public static void countTotalProducts(Session session) {

        Query<Long> query = session.createQuery("SELECT COUNT(p) FROM Product p", Long.class);
        Long count = query.uniqueResult();

        System.out.println("\nTotal Products: " + count);
    }

    // Count stock
    public static void countProductsInStock(Session session) {

        Query<Long> query = session.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0",
                Long.class);

        Long count = query.uniqueResult();

        System.out.println("Products in Stock: " + count);
    }

    // Min max price
    public static void findMinMaxPrice(Session session) {

        Query<Object[]> query = session.createQuery(
                "SELECT MIN(p.price), MAX(p.price) FROM Product p",
                Object[].class);

        Object[] result = query.uniqueResult();

        System.out.println("\nMinimum Price: " + result[0]);
        System.out.println("Maximum Price: " + result[1]);
    }

    // Price range
    public static void filterProductsByPriceRange(Session session, double min, double max) {

        Query<Product> query = session.createQuery(
                "FROM Product p WHERE p.price BETWEEN :min AND :max",
                Product.class);

        query.setParameter("min", min);
        query.setParameter("max", max);

        System.out.println("\nProducts between " + min + " and " + max);
        query.list().forEach(p -> System.out.println(p.getName() + " - $" + p.getPrice()));
    }

    // LIKE query
    public static void findProductsStartingWith(Session session, String prefix) {

        Query<Product> query = session.createQuery(
                "FROM Product p WHERE p.name LIKE :pattern",
                Product.class);

        query.setParameter("pattern", prefix + "%");

        System.out.println("\nProducts starting with " + prefix);
        query.list().forEach(p -> System.out.println(p.getName()));
    }
}