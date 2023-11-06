package com.example.NoteApplication.config.openApi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Note Application")
                        .description("Backend REST API for better note containment.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Jakub Niewadzi")
                                .email("01169600@pw.edu.pl")
                                .url("name.surname@example.com"))
                        .license(new License()
                                .name("License of API")
                                .url("API license URL")));
    }
}


