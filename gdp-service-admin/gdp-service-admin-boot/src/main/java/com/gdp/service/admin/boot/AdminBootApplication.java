package com.gdp.service.admin.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdminBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminBootApplication.class, args);
    }
}
