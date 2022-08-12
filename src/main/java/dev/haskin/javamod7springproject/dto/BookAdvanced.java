package dev.haskin.javamod7springproject.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import dev.haskin.javamod7springproject.model.Genre;
import dev.haskin.javamod7springproject.model.ReadingList;
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
    @NotEmpty
    @Min(1)
    private int pages;
    @NotEmpty
    @LastModifiedDate
    private LocalDate published;
    private Set<Genre> genres = new HashSet<>();
    private Set<ReadingList> readingList = new HashSet<>();
}
