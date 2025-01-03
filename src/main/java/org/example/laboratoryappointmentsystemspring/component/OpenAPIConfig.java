package org.example.laboratoryappointmentsystemspring.component;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;


@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme token = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .name("token")
                .in(SecurityScheme.In.HEADER);
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(HttpHeaders.AUTHORIZATION);
        return new OpenAPI()
                .schemaRequirement(HttpHeaders.AUTHORIZATION, token)
                .addSecurityItem(securityRequirement);
    }
}
