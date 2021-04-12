package com.example.examproject.security.controller;

import com.example.examproject.exceptionhandling.UserDuplicateDto;
import com.example.examproject.exceptionhandling.UserDuplicateException;
import com.example.examproject.security.dao.UserMapper;
import com.example.examproject.security.model.LoginRequestDTO;
import com.example.examproject.security.model.RegistrationRequestDTO;
import com.example.examproject.security.model.User;
import com.example.examproject.security.service.api.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * REST контроллер для работы с пользователями
 */

@Tag(name = "Пользователь", description = "API для пользователей")
@RestController
@RequestMapping("/api")
public class UserController {

    private final AuthenticationService authenticationService;

    @Autowired
    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @Operation(summary = "Вход в учетную запись")
    public Authentication login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return authenticationService.authorize(loginRequestDTO);
    }

    @PostMapping("/logout")
    @Operation(summary = "Выход из учетной записи")
    public void logout() {
        authenticationService.logout();
    }


    @PostMapping("/register")
    @Operation(summary = "Регистрация нового пользователя")
    public User register(@RequestBody @Validated RegistrationRequestDTO registrationRequestDTO) {
        return authenticationService.register(registrationRequestDTO);
    }

    @PostMapping("/current")
    @Operation(summary = "Текущий пользователь")
    public Principal current(Principal principal) {
        return principal;
    }

    @ExceptionHandler
    public ResponseEntity<UserDuplicateDto> handleException(UserDuplicateException exception) {
        UserDuplicateDto user = new UserDuplicateDto();
        user.setInfo(exception.getMessage());
        return new ResponseEntity<>(user, HttpStatus.NOT_ACCEPTABLE);
    }
}
