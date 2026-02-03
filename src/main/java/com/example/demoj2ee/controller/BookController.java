package com.example.demoj2ee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demoj2ee.model.Book;
import com.example.demoj2ee.service.BookService;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String listBooks(Model model) {
        if (bookService.getAllBooks().isEmpty()) {
            bookService.addBook(new Book(1, "1984", "George Orwell"));
            bookService.addBook(new Book(2, "Brave New World", "Aldous Huxley"));
        }
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    // thêm sách mới
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        // TODO: process POST request
        bookService.addBook(book);
        return "redirect:/books";
    }

    // hiện form sửa sách
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") int id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit-book";
    }

    // cập nhập danh sách
    @PostMapping("/edit")
    public String editBook(@ModelAttribute Book book) {
        bookService.updateBook(book.getId(), book);
        return "redirect:/books";
    }

    // xóa sách
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

}
