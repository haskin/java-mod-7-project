package dev.haskin.javamod7springproject.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReadingListBasic {
    Long id;
    @NotEmpty
    private String name;

}
