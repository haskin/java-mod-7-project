package dev.haskin.javamod7springproject.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReadingListAdvanced {
    Long id;
    @NotEmpty
    private String name;
    private Set<BookBasic> books = new HashSet<>();
}
