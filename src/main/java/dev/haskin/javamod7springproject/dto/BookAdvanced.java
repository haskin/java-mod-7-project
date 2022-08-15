package dev.haskin.javamod7springproject.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
public class BookAdvanced {
    Long id;
    @NotEmpty
    private String title;
    @NotNull
    @Min(1)
    private int pages;
    @NotNull
    private LocalDate published;
    @NotNull
    private AuthorDto author;
    @Size(min = 1)
    private Set<GenreDto> genres = new HashSet<>();
    // private Set<ReadingList> readingList = new HashSet<>();
}
