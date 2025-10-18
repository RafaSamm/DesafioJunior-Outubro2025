package br.com.rhssolutions.desafioJunior.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DocConfig {

    @Bean
    private static OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Desafio Junior - RHSSolutions")
                        .description("Desafio Junior - RHSSolutions")
                        .version("1.0.0")
                        .contact(contact()))
                .servers(List.of(new Server()
                        .url("http://localhost:8080")
                        .description("Ambiente de Desenvolvimento")));
    }


    @Bean
    private static Contact contact() {
        return new Contact()
                .name("RHSSolutions - Inovação e Qualidade de Software")
                .email("rhssolutions@gmail.com")
                .url("https://www.rhssolutions.com.br");
    }
}
