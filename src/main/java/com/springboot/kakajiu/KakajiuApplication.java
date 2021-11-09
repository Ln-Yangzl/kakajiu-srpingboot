package com.springboot.kakajiu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class KakajiuApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakajiuApplication.class, args);
    }

}
