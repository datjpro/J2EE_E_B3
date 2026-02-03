package com.example.demoj2ee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demoj2ee.model.Book;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    public void addBook(Book book) {
        int maxId = books.stream().mapToInt(Book::getId).max().orElse(0);
        book.setId(maxId + 1);
        books.add(book);
    }

    public void updateBook(int id, Book updatedBook) {
        books.stream().filter(book -> book.getId() == id).findFirst().ifPresent(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
        });
    }

    public void deleteBook(int id) {
        books.removeIf(book -> book.getId() == id);
    }

}
