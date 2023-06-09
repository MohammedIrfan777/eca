package com.eca.catalog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerOpenApiConfig {

	@Value("${eca.openapi.dev-url}")
	private String devUrl;

	@Value("${eca.openapi.prod-url}")
	private String prodUrl;

	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("Server URL in Development environment");

		Server prodServer = new Server();
		prodServer.setUrl(prodUrl);
		prodServer.setDescription("Server URL in Production environment");

		Contact contact = new Contact();
		contact.setEmail("eca-academy@gmail.com");
		contact.setName("eca academy");
		contact.setUrl("https://www.eca-academy.com");

		License mitLicense = new License().name("PS License").url("https://eca.com/licenses/ps/");

		Info info = new Info().title("ECA Apartment Management API").version("1.0").contact(contact)
				.description("This API exposes endpoints to manage tutorials.")
				.termsOfService("https://www.eca-academy.com/terms").license(mitLicense);

		return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	}
}