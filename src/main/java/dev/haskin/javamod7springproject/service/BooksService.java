package dev.haskin.javamod7springproject.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.haskin.javamod7springproject.model.Book;
import dev.haskin.javamod7springproject.repository.BookRepository;

@Service
public class BooksService {
    @Autowired
    private BookRepository bookRepository;

    List<Book> getAllBooksFromIds(Set<Long> ids) {
        return bookRepository.findAllById(ids);
    }
}
