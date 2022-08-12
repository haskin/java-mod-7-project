package dev.haskin.javamod7springproject.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestUserDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
