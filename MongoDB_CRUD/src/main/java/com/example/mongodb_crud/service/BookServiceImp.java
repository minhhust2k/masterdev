package com.example.mongodb_crud.service;

import com.example.mongodb_crud.exception.NotFoundException;
import com.example.mongodb_crud.model.Book;
import com.example.mongodb_crud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Book findById(String id) throws NotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            return book.get();
        }
        else {
            throw new NotFoundException("Book not found");
        }
    }

    @Override
    public List<Book> findByName(String name) {
        return bookRepository.findAllByName(name);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findAllByAuthor(author);
    }

    @Override
    public List<Book> findByDesc(String desc) {
        return bookRepository.findAllByDescriptionContains(desc);
    }
    @Override
    public List<Book> insertMany(List<Book> books){
        return bookRepository.insert(books);
    }

    @Override
    public Book insertOne(Book book){
        return bookRepository.insert(book);
    }

    @Override
    public Page<Book> readAll(int page, int size) {
        Page<Book> sortedByDay = bookRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "publishDay")));

        return sortedByDay;
    }

    @Override
    public Book updateBook(String id,Book book){
        Book b = this.findById(id);
        b.setAuthor(book.getAuthor());
        b.setName(book.getName());
        b.setPublishDay(book.getPublishDay());
        b.setDescription(book.getDescription());

        return bookRepository.save(b);
    }

    @Override
    public Map<String, String> delete(String id) {

        // Total count of data before delete
        long beforeDelete = bookRepository.count();

        bookRepository.deleteById(id);

        // Total count of data after delete
        long afterDelete = bookRepository.count();

        String messageValue = beforeDelete == afterDelete ? "Something went wrong!" : "Record deleted";

        Map<String, String> deleteMap = new HashMap<>();
        deleteMap.put("message", messageValue);

        return deleteMap;
    }

    @Override
    public List<Book> searchFullText(String text) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(text);
        List<Book> books = mongoTemplate.find(query(criteria), Book.class);
        return books;
    }

    @Override
    public List<Book> searchByDay (Date date1, Date date2) {
        Query query = new Query();
        query.addCriteria(Criteria.where("publishDay").lt(date2).gt(date1));
        List<Book> books = mongoTemplate.find(query, Book.class);
        return books;
    }

    @Override
    public List<Book> searchFullTextSortByScore(String text) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(text);
        TextQuery query = new TextQuery(criteria);
        query.setScoreFieldName("score");
        query.sortByScore();
        return mongoTemplate.find(query, Book.class);
    }
}
