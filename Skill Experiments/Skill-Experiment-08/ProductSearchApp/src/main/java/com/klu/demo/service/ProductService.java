package com.klu.demo.service;

import com.klu.demo.entity.Product;
import com.klu.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Product save(Product product) {
        return repo.save(product);
    }

    public List<Product> getByCategory(String category) {
        return repo.findByCategory(category);
    }

    public List<Product> getByPriceRange(double min, double max) {
        return repo.findByPriceBetween(min, max);
    }

    public List<Product> getSortedProducts() {
        return repo.getAllSortedByPrice();
    }

    public List<Product> getExpensive(double price) {
        return repo.getExpensiveProducts(price);
    }
}