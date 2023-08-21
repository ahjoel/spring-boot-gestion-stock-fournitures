package com.gestock.fourniture.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${stock.openapi.dev-url}")
    private String devUrl;

    @Value("${stock.openapi.prod-url}")
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
        contact.setEmail("aholoujoel5@gmail.com");
        contact.setName("Joel");
        //contact.setUrl("https://www.scolarite.com");

        //License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("API de Gestion de Stock des Fournitures de Bureaux")
                .version("1.0")
                .contact(contact)
                //.description("Cet API expose les endpoints permettant la gestion de scolarité des étudiants.").termsOfService("https://www.scolarite.com/terms");
                .description("Cet API expose les endpoints permettant la gestion de stock des fournitures de bureaux en entreprise.");
        //.license(mitLicense);

        //return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
        return new OpenAPI().info(info).servers(Arrays.asList(devServer, prodServer));
    }
}
