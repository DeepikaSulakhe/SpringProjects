package com.inventorymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
// this annotation helps to let servers know that this project is a eureka client (annotation is used on the main application class to 
//explicitly enable the Eureka client features)
@EnableDiscoveryClient 
public class InventoryManagementSystemProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystemProductApplication.class, args);
	}

}
