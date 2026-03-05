package com.erp.erp_layout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ErpLayoutApplication {
    public static void main(String[] args) {
        SpringApplication.run(ErpLayoutApplication.class, args);
    }
}