package com.klu.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.klu.demo.model.Book;

@RestController
public class LibraryController {

    // In-memory list
    List<Book> bookList = new ArrayList<>();

    // 1. Welcome API
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Online Library System";
    }

    // 2. Count API
    @GetMapping("/count")
    public int count() {
        return 10;
    }

    // 3. Price API
    @GetMapping("/price")
    public double price() {
        return 499.99;
    }

    // 4. Books list
    @GetMapping("/books")
    public List<String> books() {
        return Arrays.asList("Java", "Spring", "Python");
    }

    // 5. Book by ID
    @GetMapping("/books/{id}")
    public String bookById(@PathVariable int id) {
        return "Book details for ID: " + id;
    }

    // 6. Search book using request param
    @GetMapping("/search")
    public String search(@RequestParam String title) {
        return "Searching for book: " + title;
    }

    // 7. Author name
    @GetMapping("/author/{name}")
    public String author(@PathVariable String name) {
        return "Books written by: " + name;
    }

    // 8. Add book (POST)
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookList.add(book);
        return "Book added successfully";
    }

    // 9. View all books
    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return bookList;
    }
}