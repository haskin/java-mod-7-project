package dev.haskin.javamod7springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.haskin.javamod7springproject.dto.BookBasic;
import dev.haskin.javamod7springproject.service.GenreService;

@RestController
@RequestMapping("api/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/{id}/books")
    List<BookBasic> getAllBooks(@PathVariable(value = "id") Long genreId) {
        return genreService.getAllBooks(genreId);
    }
}
