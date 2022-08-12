package dev.haskin.javamod7springproject.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import dev.haskin.javamod7springproject.model.ReadingList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserAdvanced {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @JsonInclude(Include.NON_EMPTY)
    Set<ReadingList> readingList = new HashSet<>();
}
