package com.matao.springconsul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringConsulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringConsulApplication.class, args);
    }

}
