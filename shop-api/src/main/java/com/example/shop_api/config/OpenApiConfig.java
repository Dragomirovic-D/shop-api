package com.example.shop_api.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI shopApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shop API")
                        .description("Spring Boot REST API for product catalog and shopping cart")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Your Name")
                                .email("your@email.com")
                                .url("https://your-website.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://github.com/your-repo"));
    }
}
