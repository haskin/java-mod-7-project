package dev.haskin.javamod7springproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.haskin.javamod7springproject.dto.BookAdvanced;
import dev.haskin.javamod7springproject.dto.BookBasic;
import dev.haskin.javamod7springproject.service.BookService;

@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 
     * @return List of BookBasic
     */
    @GetMapping
    public List<BookBasic> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookAdvanced getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookBasic createBook(@Valid @RequestBody BookAdvanced book) {
        return bookService.createBook(book);
    }

    @PutMapping
    public BookBasic updateBook(@Valid @RequestBody BookAdvanced book) {
        return bookService.updateBook(book);
    }
}
