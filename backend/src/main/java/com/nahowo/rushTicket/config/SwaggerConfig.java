package com.nahowo.rushTicket.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .components(new Components())
            .info(apiInfo());
    }
    private Info apiInfo() {
        return new Info()
            .title("rushTicket API")
            .description("rushTicket의 API 명세")
            .version("1.0.0");
    }
}
