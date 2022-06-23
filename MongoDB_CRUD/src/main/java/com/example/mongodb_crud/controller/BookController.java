package com.example.mongodb_crud.controller;

import com.example.mongodb_crud.model.Book;
import com.example.mongodb_crud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/insert-one")
    public Book insertOne(@RequestBody Book book) {

        return bookService.insertOne(book);
    }

    @PostMapping("/insert-many")
    public List<Book> insertMany(@RequestBody List<Book> books) {

        return bookService.insertMany(books);
    }

    @GetMapping("/get-all-books/{page}/{size}")
    public Page<Book> getAllBooks(@PathVariable("page") int page, @PathVariable("size") int size) {
        return bookService.readAll(page, size);
    }

    @GetMapping("/find-by-id/{id}")
    public Book findById(@PathVariable("id") String id) {
        return bookService.findById(id);
    }

    @GetMapping("/find-by-name/{name}")
    public List<Book> findByName(@PathVariable("name") String name) {
        return bookService.findByName(name);
    }


    @GetMapping("/find-by-author/{author}")
    public List<Book> findByAuthor(@PathVariable("author") String author) {
        return bookService.findByAuthor(author);
    }

    @GetMapping("/find-by-desc/{desc}")
    public List<Book> findByDesc(@PathVariable("desc") String desc) {
        return bookService.findByDesc(desc);
    }

    @PutMapping("/update-book/{id}")
    public Book update(@PathVariable("id") String id ,@RequestBody Book book) {
        return bookService.updateBook(id,book);
    }

    @DeleteMapping("/delete-book/{id}")
    public Map<String, String> deleteSchool(@PathVariable String id) {
        return bookService.delete(id);
    }

    @GetMapping("/between-date/{date1}/{date2}")
    public List<Book> searchDay(@PathVariable("date1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1, @PathVariable("date2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2) {
        return bookService.searchByDay(date1, date2);
    }

    @GetMapping("/full-text-search/{text}")
    public List<Book> searchFullText(@PathVariable("text") String text) {
        return bookService.searchFullText(text);
    }

    @GetMapping("/full-text-search-sort-by-score/{text}")
    public List<Book> searchFullTextSortByScore(@PathVariable("text") String text) {
        return bookService.searchFullTextSortByScore(text);
    }

}
