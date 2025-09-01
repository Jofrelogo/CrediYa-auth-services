package com.crediya.auth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Auth Service API")
                        .version("1.0")
                        .description("Documentación de los endpoints del servicio de autenticación")
                        .contact(new Contact()
                                .name("Equipo CrediYa")
                                .email("soporte@crediya.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }
}

