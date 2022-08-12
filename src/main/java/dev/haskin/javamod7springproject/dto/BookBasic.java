package dev.haskin.javamod7springproject.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.LastModifiedDate;

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
}
