package dev.haskin.javamod7springproject.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GenreDto {
    @JsonInclude(Include.NON_NULL)
    private Long id;
    @NotEmpty
    private String name;

    public void setName(String name) {
        this.name = name.trim().toLowerCase();
    }
}
