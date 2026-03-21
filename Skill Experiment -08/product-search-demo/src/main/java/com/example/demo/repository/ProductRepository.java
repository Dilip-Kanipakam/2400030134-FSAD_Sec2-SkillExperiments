package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Derived Queries
    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(double min, double max);

    // JPQL Queries
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> getAllSorted();

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> getExpensiveProducts(double price);

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> getByCategoryJPQL(String category);
}