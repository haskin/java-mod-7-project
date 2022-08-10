package dev.haskin.javamod7springproject.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestUserDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
