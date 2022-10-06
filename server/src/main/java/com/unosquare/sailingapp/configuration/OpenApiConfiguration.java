package com.unosquare.sailingapp.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Value("${springdoc.api.title}")
    private String title;

    @Value("${springdoc.api.description}")
    private String description;

    @Bean
    public OpenAPI setUpOpenAPI() {
        return new OpenAPI()
                .info(info())
                .components(components())
                .addSecurityItem(securityItem());
    }

    private Info info() {
        return new Info().title(this.title).description(this.description);
    }

    private Components components() {
        return new Components().addSecuritySchemes("Bearer",
                new SecurityScheme().type(Type.APIKEY).name("Authorization").in(In.HEADER));
    }

    private SecurityRequirement securityItem() {
        return new SecurityRequirement().addList("Bearer");
    }
}
