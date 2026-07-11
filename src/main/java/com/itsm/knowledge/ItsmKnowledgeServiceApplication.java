package com.itsm.knowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ItsmKnowledgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItsmKnowledgeServiceApplication.class, args);
    }
}
