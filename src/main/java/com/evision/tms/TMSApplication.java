package com.evision.tms;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@Slf4j
@SpringBootApplication
public class TMSApplication {
	public static void main(String[] args) {
		SpringApplication.run(TMSApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		log.info("Inside Class: TMSApplication , Method: customOpenAPI ");
		final String securitySchemeName = "Access Token";
		final SecurityScheme securitySchemesItem = new SecurityScheme();
		securitySchemesItem.setType(SecurityScheme.Type.HTTP);
		securitySchemesItem.setDescription("Authentication Header");
		securitySchemesItem.setScheme("bearer");
		securitySchemesItem.setBearerFormat("JWT");
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.components(new Components().addSecuritySchemes(securitySchemeName, securitySchemesItem));

	}
}
