package com.example.mongodb_crud.repository;

import com.example.mongodb_crud.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findAllByName(String name);

    List<Book> findAllByAuthor(String author);

    List<Book> findAllByDescriptionContains(String desc);

}
