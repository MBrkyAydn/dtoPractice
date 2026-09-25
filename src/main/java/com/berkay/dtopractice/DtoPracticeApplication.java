package com.berkay.dtopractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DtoPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DtoPracticeApplication.class, args);
    }

}
