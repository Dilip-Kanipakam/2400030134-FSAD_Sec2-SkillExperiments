package com.inventory.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;
import com.inventory.loader.ProductDataLoader;
import java.util.List;

public class HQLDemo {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
          

        	ProductDataLoader.loadSampleProducts(session); 
            

            sortProductsByPriceAscending(session);
 
         
            sortProductsByPriceAscending(session);
            sortProductsByPriceDescending(session);

           
            sortProductsByQuantityDescending(session);

       
            getFirstThreeProducts(session);
            getNextThreeProducts(session);

            
            countTotalProducts(session);
            countProductsInStock(session);
            findMinMaxPrice(session);

            
            groupProductsByDescription(session);


            filterProductsByPriceRange(session, 20.0, 100.0);

            findProductsStartingWith(session, "D");
            findProductsContaining(session, "Desk");

        } finally {
            session.close();
            factory.close();
        }
    }


    public static void sortProductsByPriceAscending(Session session) {
        String hql = "FROM Product p ORDER BY p.price ASC";
        Query<Product> query = session.createQuery(hql, Product.class);
        System.out.println("\n=== Price Ascending ===");
        query.list().forEach(System.out::println);
    }

    public static void sortProductsByPriceDescending(Session session) {
        String hql = "FROM Product p ORDER BY p.price DESC";
        Query<Product> query = session.createQuery(hql, Product.class);
        System.out.println("\n=== Price Descending ===");
        query.list().forEach(System.out::println);
    }

    public static void sortProductsByQuantityDescending(Session session) {
        String hql = "FROM Product p ORDER BY p.quantity DESC";
        Query<Product> query = session.createQuery(hql, Product.class);
        System.out.println("\n=== Quantity Descending (Highest First) ===");
        query.list().forEach(p -> System.out.println(p.getName() + " - Qty: " + p.getQuantity()));
    }

    public static void getFirstThreeProducts(Session session) {
        Query<Product> query = session.createQuery("FROM Product p", Product.class);
        query.setFirstResult(0);
        query.setMaxResults(3);
        System.out.println("\n=== Pagination: First 3 Products ===");
        query.list().forEach(System.out::println);
    }

    public static void getNextThreeProducts(Session session) {
        Query<Product> query = session.createQuery("FROM Product p", Product.class);
        query.setFirstResult(3);
        query.setMaxResults(3);
        System.out.println("\n=== Pagination: Next 3 Products ===");
        query.list().forEach(System.out::println);
    }

    public static void countTotalProducts(Session session) {
        Query<Long> query = session.createQuery("SELECT COUNT(p) FROM Product p", Long.class);
        System.out.println("\nTotal Products: " + query.uniqueResult());
    }

    public static void countProductsInStock(Session session) {
        Query<Long> query = session.createQuery("SELECT COUNT(p) FROM Product p WHERE p.quantity > 0", Long.class);
        System.out.println("Products in Stock: " + query.uniqueResult());
    }

    public static void findMinMaxPrice(Session session) {
        Query<Object[]> query = session.createQuery("SELECT MIN(p.price), MAX(p.price) FROM Product p", Object[].class);
        Object[] res = query.uniqueResult();
        System.out.println("Min Price: $" + res[0] + " | Max Price: $" + res[1]);
    }

    public static void groupProductsByDescription(Session session) {
        String hql = "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        System.out.println("\n=== Groups by Description ===");
        for (Object[] row : query.list()) {
            System.out.println(row[0] + ": " + row[1] + " items");
        }
    }

    public static void filterProductsByPriceRange(Session session, double min, double max) {
        String hql = "FROM Product p WHERE p.price BETWEEN :min AND :max";
        Query<Product> query = session.createQuery(hql, Product.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        System.out.println("\n=== Products between $" + min + " and $" + max + " ===");
        query.list().forEach(p -> System.out.println(p.getName() + " ($" + p.getPrice() + ")"));
    }

    public static void findProductsStartingWith(Session session, String prefix) {
        Query<Product> query = session.createQuery("FROM Product p WHERE p.name LIKE :pat", Product.class);
        query.setParameter("pat", prefix + "%");
        System.out.println("\n=== Names starting with '" + prefix + "' ===");
        query.list().forEach(p -> System.out.println(p.getName()));
    }

    public static void findProductsContaining(Session session, String sub) {
        Query<Product> query = session.createQuery("FROM Product p WHERE p.name LIKE :pat", Product.class);
        query.setParameter("pat", "%" + sub + "%");
        System.out.println("\n=== Names containing '" + sub + "' ===");
        query.list().forEach(p -> System.out.println(p.getName()));
    }
}