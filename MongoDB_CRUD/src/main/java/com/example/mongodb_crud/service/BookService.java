package com.example.mongodb_crud.service;

import com.example.mongodb_crud.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public interface BookService {
    Book findById(String id);
    List<Book> findByName(String name);
    List<Book> findByAuthor(String author);
    List<Book> findByDesc(String desc);
    Book insertOne(Book book);
    List<Book> insertMany(List<Book> books);

    Page<Book> readAll(int page, int size);

    Book updateBook(String id,Book book);

    Map<String, String> delete(String id);

    List<Book> searchByDay(Date date1, Date date2);

    List<Book> searchFullText(String text);
    List<Book> searchFullTextSortByScore(String text);
}
