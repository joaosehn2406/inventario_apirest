package com.jceco.inventario_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.GroupedOpenApi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomiser;

@Configuration
public class SwaggerConfig {

   @Bean
   public OpenAPI openAPI() {
	   return new OpenAPI().info(new Info().title("Spring Boot REST API").description("Spring Boot Rest Api")
			   .contact(new Contact().name("TESTE")).version("1.0.0"));
   }
}
