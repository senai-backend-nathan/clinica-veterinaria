package br.com.senai.clinica.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@Configuration
@OpenAPIDefinition(
    info = @info(
        tittle = "Clinica API",
        version = "1.0",
        description = "API para o sistema clinica vetererinaria"
    )
)
public class Swagger {
    
}
