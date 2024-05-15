package com.kadiraksoy.userservice;

import com.kadiraksoy.userservice.dto.CreateUserRequest;
import com.kadiraksoy.userservice.model.User;
import com.kadiraksoy.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserService userService){

		return args -> {
			CreateUserRequest createUserRequest = new CreateUserRequest();
			createUserRequest.setFirstName("exapmle firstname");
			createUserRequest.setLastName("exapmle lastname");
			createUserRequest.setEmail("example@example.com");
			createUserRequest.setPassword("password");

			userService.createUser(createUserRequest);
		};
	}

}
