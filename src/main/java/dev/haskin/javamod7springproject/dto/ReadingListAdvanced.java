package dev.haskin.javamod7springproject.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import dev.haskin.javamod7springproject.model.Book;

public class ReadingListAdvanced {
    Long id;
    @NotEmpty
    private String name;
    private Set<Book> books = new HashSet<>();
}
