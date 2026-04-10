package com.klu.demo.repository;

import com.klu.demo.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Derived Queries
    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(double min, double max);

    // JPQL Queries
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> getAllSortedByPrice();

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> getExpensiveProducts(@Param("price") double price);

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> getByCategoryJPQL(@Param("category") String category);
}