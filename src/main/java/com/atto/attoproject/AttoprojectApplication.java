package com.atto.attoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AttoprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttoprojectApplication.class, args);
    }

}
