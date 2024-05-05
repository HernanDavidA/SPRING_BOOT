package com.riwi.vacants.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@Configuration
@OpenAPIDefinition(info = @Info(
    title = "Api para administracion de compañias y vacantes",
    version = "1.0",
    description = "Esta API fue creada para aprender los fundamentos de springboot y spring JPA"
))
public class OpenApiConfig {
    
}
