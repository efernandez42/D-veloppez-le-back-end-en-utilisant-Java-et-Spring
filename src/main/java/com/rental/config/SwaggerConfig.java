package com.rental.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "API rental",
                version = "1.0.0",
                description = "API pour la gestion des locations immobilières",
                contact = @io.swagger.v3.oas.annotations.info.Contact(name = "Emma Fernandez", email = "e.fernandez42350@gmail.com")
        ),
        servers = @Server(url = "http://localhost:8080", description = "Serveur local")
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI rentalOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .info(new Info()
                        .title("API rental")
                        .description("API pour la gestion des locations immobilières ")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Emma Fernandez")
                                .email("e.fernandez42350@gmail.com")));
    }
}