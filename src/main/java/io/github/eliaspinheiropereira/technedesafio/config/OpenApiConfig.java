package io.github.eliaspinheiropereira.technedesafio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Sistema de Educação")
                        .description("API REST para gerenciamento de alunos, cursos, disciplinas, turmas e matrículas")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Elias Pinheiro Pereira")
                                .email("eliaspinheiropereiraa@gmail.com")
                                .url("https://github.com/eliaspinheiropereira"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}

