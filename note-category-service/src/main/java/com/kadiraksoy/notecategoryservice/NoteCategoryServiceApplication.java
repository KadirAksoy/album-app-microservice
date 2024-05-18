package com.kadiraksoy.notecategoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NoteCategoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteCategoryServiceApplication.class, args);
	}

}
