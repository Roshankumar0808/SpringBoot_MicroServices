package com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBootEcommerceMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEcommerceMicroserviceApplication.class, args);
	}

}
