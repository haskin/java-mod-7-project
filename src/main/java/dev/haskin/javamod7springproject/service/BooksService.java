package dev.haskin.javamod7springproject.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.haskin.javamod7springproject.dto.BookAdvanced;
import dev.haskin.javamod7springproject.dto.BookBasic;
import dev.haskin.javamod7springproject.model.Book;
import dev.haskin.javamod7springproject.repository.BookRepository;

@Service
public class BooksService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Gets a list of all books from repository.
     * Sets the author of the list to null in order to
     * hide this field when converting to JSON.
     * 
     * @return
     */
    public List<BookBasic> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookBasic.class))
                .map(bookBasic -> {
                    bookBasic.setAuthor(null);
                    return bookBasic;
                })
                .collect(Collectors.toList());
    }

    /**
     * Gets a book by id and converts it into BookAdvancded.
     * Genre id is set to null to hid this field when converting
     * to JSON.
     * 
     * @param id
     * @return
     */
    public BookAdvanced getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id: " + id + " was not found"));
        book.getGenres().forEach(genre -> genre.setId(null));
        return modelMapper.map(book, BookAdvanced.class);
    }

    public List<Book> getAllBooksFromIds(Set<Long> ids) {
        return bookRepository.findAllById(ids);
    }
}
