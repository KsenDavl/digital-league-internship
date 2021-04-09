package com.example.examproject.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс - конфигурация работы со Swagger.
 */

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Teachers and Students API")
                .version(("1.0.1"))
                .contact(new Contact()
                        .email("davlk@mail.ru")
                        .name("Davletova Kseniya")
                ));
    }
}
