package com.rental.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI rentalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API rental")
                        .description("API pour la gestion des locations immobilières ")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Emma Fernandez")
                                .email("e.fernandez42350@gmail.com")));
    }
}