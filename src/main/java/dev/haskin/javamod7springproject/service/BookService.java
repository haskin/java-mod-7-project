package dev.haskin.javamod7springproject.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.haskin.javamod7springproject.dto.BookAdvanced;
import dev.haskin.javamod7springproject.dto.BookBasic;
import dev.haskin.javamod7springproject.model.Author;
import dev.haskin.javamod7springproject.model.Book;
import dev.haskin.javamod7springproject.model.Genre;
import dev.haskin.javamod7springproject.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private AuthorService authorService;

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

    /**
     * Creates a book.
     * Limitations: cannot create a Book with an invalid Author or Genre
     * Genres are searched by "name" field. Authors are searched by "id"
     * 
     * @param bookAdvanced
     * @return
     */
    @Transactional
    public BookBasic createBook(BookAdvanced bookAdvanced) {
        if (bookAdvanced.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id of Book cannot be provided by the User");
        }
        Book book = modelMapper.map(bookAdvanced, Book.class);
        log.debug("Getting all genres by Genre name property");
        Set<Genre> genres = genreService
                .getAllGenresByNames(book.getGenres().stream()
                        .map(Genre::getName).collect(Collectors.toSet()));
        if (genres.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genres entered were invalid");
        }
        book.setGenres(genres);
        log.debug("Getting Author by id from create book");
        Long authorId = Optional.ofNullable(book.getAuthor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Author id cannot by null"));
        Author author = authorService.getAuthorById(authorId);
        book.setAuthor(author);
        book = bookRepository.save(book);
        log.debug("Author set to null to hide author in JSON response");
        book.setAuthor(null);
        return modelMapper.map(book, BookBasic.class);
    }

    /**
     * Updates the book, based on new values.
     * 
     * Limitations: New Genres and Authors cannot be created, however
     * the book genre and author can be changed to ones in the
     * database. Genre is chosen by genre name, and author is chosen
     * by author id.
     * 
     * @param bookAdvanced
     * @return
     */
    @Transactional
    public BookBasic updateBook(BookAdvanced bookAdvanced) {
        Book databaseBook = bookRepository.findById(bookAdvanced.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Book id: " + bookAdvanced.getId() + " was not found"));
        Book book = modelMapper.map(bookAdvanced, Book.class);
        // Genres
        Set<Genre> genres = genreService
                .getAllGenresByNames(book.getGenres().stream()
                        .map(Genre::getName).collect(Collectors.toSet()));
        if (genres.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genres entered were invalid");
        }
        book.setGenres(genres);
        for (Genre genre : genres) {
            genre.getBooks().add(book);
        }
        Long authorId = Optional.ofNullable(book.getAuthor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Author id cannot by null"));
        Author author = authorService.getAuthorById(authorId);
        book.setAuthor(author);
        book = bookRepository.save(book);
        BookBasic bookBasic = modelMapper.map(book, BookBasic.class);
        log.debug("Author set to null to hide author in JSON response");
        bookBasic.setAuthor(null);
        return bookBasic;
    }

    public List<Book> getAllBooksFromIds(Set<Long> ids) {
        return bookRepository.findAllById(ids);
    }
}
