package com.example.pc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.pc.model")
@EnableJpaRepositories(basePackages = "com.example.pc.repository")
public class PremierConstructionApplication {
    public static void main(String[] args) {
        SpringApplication.run(PremierConstructionApplication.class, args);
    }
}
