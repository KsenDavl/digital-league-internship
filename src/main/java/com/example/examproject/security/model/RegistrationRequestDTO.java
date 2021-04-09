package com.example.examproject.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    private int role_id;

}
