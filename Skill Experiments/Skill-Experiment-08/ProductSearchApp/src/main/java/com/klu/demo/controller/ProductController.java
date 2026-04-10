package com.klu.demo.controller;

import com.klu.demo.entity.Product;
import com.klu.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // Add Product
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return service.save(product);
    }

    // Category Search
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    // Price Filter
    @GetMapping("/filter")
    public List<Product> getByPriceRange(@RequestParam double min,
                                         @RequestParam double max) {
        return service.getByPriceRange(min, max);
    }

    // Sort by Price
    @GetMapping("/sorted")
    public List<Product> getSorted() {
        return service.getSortedProducts();
    }

    // Expensive Products
    @GetMapping("/expensive/{price}")
    public List<Product> getExpensive(@PathVariable double price) {
        return service.getExpensive(price);
    }
}