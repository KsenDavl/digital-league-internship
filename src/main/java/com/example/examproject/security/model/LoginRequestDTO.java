package com.example.examproject.security.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Модель данных авторизации")
public class LoginRequestDTO {

    @NotNull
    @Schema(title = "Логин")
    private String login;

    @NotNull
    @Schema(title = "Пароль")
    private String password;
}
