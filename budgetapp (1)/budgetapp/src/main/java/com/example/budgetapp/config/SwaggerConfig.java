package com.example.budgetapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Budget Management API",
                version = "1.0",
                description = "API for managing budgets, expenses, and incomes"
        )
)
public class SwaggerConfig {
}

