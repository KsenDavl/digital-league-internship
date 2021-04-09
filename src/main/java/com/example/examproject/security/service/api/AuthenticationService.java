package com.example.examproject.security.service.api;

import com.example.examproject.security.model.LoginRequestDTO;
import com.example.examproject.security.model.RegistrationRequestDTO;
import com.example.examproject.security.model.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    Authentication authorize(LoginRequestDTO loginRequestDTO);

    User register(RegistrationRequestDTO registrationRequestDTO);

    void logout();

}
