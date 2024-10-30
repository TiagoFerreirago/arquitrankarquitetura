package com.architrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAi {

	@Bean
	OpenAPI customOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("RestFull application for structuring architects using Java 18, Spring Boot 3.2.0 and Docker")
						.description("Application to assist architects in projects")
						.version("v1")
						.termsOfService("www.architrack.com")
						.license(new License().name("Apache 2.0").url("www.architrack.com/license")));
				
	}
}
