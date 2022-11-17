package com.nocontry.ecommerce;

import com.nocontry.ecommerce.entities.AppUser;
import com.nocontry.ecommerce.entities.Role;
import com.nocontry.ecommerce.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new AppUser(null,"Donovan Ian","donovan",new BCryptPasswordEncoder().encode("1234"),new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Mia Mauren","mia",new BCryptPasswordEncoder().encode("1234"),new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Will Smith","will",new BCryptPasswordEncoder().encode("1234"),new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Juan Manuel","juancho",new BCryptPasswordEncoder().encode("1234"),new ArrayList<>()));

			userService.addRoleToUser("donovan","ROLE_USER");
			userService.addRoleToUser("mia","ROLE_MANAGER");
			userService.addRoleToUser("will","ROLE_ADMIN");
			userService.addRoleToUser("juancho","ROLE_SUPER_ADMIN");
			userService.addRoleToUser("juancho","ROLE_ADMIN");
			userService.addRoleToUser("juancho","ROLE_MANAGER");
			userService.addRoleToUser("juancho","ROLE_USER");
		};
	}

}
