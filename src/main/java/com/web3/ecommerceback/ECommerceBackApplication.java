package com.web3.ecommerceback;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class ECommerceBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceBackApplication.class, args);
    }

}
