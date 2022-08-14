package dev.haskin.javamod7springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.haskin.javamod7springproject.dto.BookAdvanced;
import dev.haskin.javamod7springproject.dto.BookBasic;
import dev.haskin.javamod7springproject.service.BooksService;

@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BooksService booksService;

    /**
     * 
     * @return List of BookBasic
     */
    @GetMapping
    public List<BookBasic> getBooks() {
        return booksService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookAdvanced getBookById(@PathVariable Long id) {
        return booksService.getBookById(id);
    }
}
