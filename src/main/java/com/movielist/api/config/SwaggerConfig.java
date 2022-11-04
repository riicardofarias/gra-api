package com.movielist.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI customOpenAPI() {
        var apiInfo = new Info()
            .title("Golden Raspberry Awards - API")
            .description("Apresentação dos filmes vencedores do Golden Raspberry Awards")
        .version("1.0.0");

        return new OpenAPI().info(apiInfo);
    }
}
