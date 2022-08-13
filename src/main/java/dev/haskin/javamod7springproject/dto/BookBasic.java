package dev.haskin.javamod7springproject.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookBasic {
    Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    @Min(1)
    private int pages;
    @NotEmpty
    @LastModifiedDate
    private LocalDate published;
    @JsonInclude(Include.NON_NULL)
    private AuthorDto author;
}
