package com.example.examproject.security.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Модель данных регистрации нового пользователя")
public class RegistrationRequestDTO {

    @NotNull
    @Schema(title = "Имя пользователя")
    private String username;

    @NotNull
    @Schema(title = "Пароль")
    private String password;

    @NotNull
    @Schema(title = "Идентификатор роли")
    private int role_id;

}
