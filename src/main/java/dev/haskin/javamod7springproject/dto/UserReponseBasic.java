package dev.haskin.javamod7springproject.dto;

import javax.validation.constraints.NotEmpty;

public class UserReponseBasic {
    @NotEmpty
    private String username;
}
