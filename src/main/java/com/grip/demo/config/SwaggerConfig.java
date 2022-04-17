package com.grip.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI restAPI() {
        Info info = new Info()
                .title("Demo API")
                .version("0.0.1")
                .description("데모")
                .termsOfService("")
                .contact(new Contact().name("이지영").url("https://tuliplee.tk").email("com8599@gmail.com"));

        List<Server> servers = List.of(new Server().url("http://localhost:8080").description(""));

        return new OpenAPI()
                .info(info)
                .servers(servers);
    }
}
