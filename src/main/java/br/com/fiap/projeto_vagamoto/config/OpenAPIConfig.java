package br.com.fiap.projeto_vagamoto.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API Vaga Moto",
        version = "1.0",
        description = "Documentação da API de gerenciamento de vagas de moto"
    )
)
public class OpenAPIConfig {
}