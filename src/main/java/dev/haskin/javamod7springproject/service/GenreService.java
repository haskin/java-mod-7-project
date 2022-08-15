package dev.haskin.javamod7springproject.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.haskin.javamod7springproject.dto.BookBasic;
import dev.haskin.javamod7springproject.model.Genre;
import dev.haskin.javamod7springproject.repository.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "The genre id: " + id + " was not found"));
    }

    /**
     * Returns a list of books mapped to BookBasic
     * from the given Genre Id. Author is set to null
     * as to not be shown when converted to JSON.
     * If the genre is not found an empty list is returned
     * 
     * @param genreId
     * @return
     */
    public List<BookBasic> getAllBooks(Long genreId) {
        try {
            Genre genre = genreRepository.getReferenceById(genreId);
            return genre.getBooks().stream()
                    .map(book -> {
                        book.setAuthor(null);
                        return book;
                    })
                    .map(book -> modelMapper.map(book, BookBasic.class))
                    .collect(Collectors.toList());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The genre with id: " + genreId + " was not found");
        }
    }

    public Set<Genre> getAllGenresByNames(Set<String> names) {
        return genreRepository.findAllByNameIn(names);
    }
}
