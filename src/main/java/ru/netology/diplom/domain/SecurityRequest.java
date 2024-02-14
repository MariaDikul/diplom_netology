package ru.netology.diplom.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SecurityRequest {

    @NotBlank(message = "login must not be null")
    private String login;

    @NotBlank(message = "password must not be null")
    private String password;
}
