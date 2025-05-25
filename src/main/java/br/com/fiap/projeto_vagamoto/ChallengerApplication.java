package br.com.fiap.projeto_vagamoto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@EntityScan
@ComponentScan
@EnableCaching
@SpringBootApplication
public class ChallengerApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ChallengerApplication.class, args);
    }
}
