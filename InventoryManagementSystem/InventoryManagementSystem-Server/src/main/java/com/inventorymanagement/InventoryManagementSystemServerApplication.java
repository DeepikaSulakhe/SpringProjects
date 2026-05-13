package com.inventorymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication

//in Spring Cloud transforms a standard Spring Boot application into a Netflix Eureka Service Registry
@EnableEurekaServer
public class InventoryManagementSystemServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystemServerApplication.class, args);
	}

}
