package com.nocontry.ecommerce;

import com.nocontry.ecommerce.entities.AppUser;
import com.nocontry.ecommerce.entities.RoleEntity;
import com.nocontry.ecommerce.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new RoleEntity(null,"ROLE_USER",null));
			userService.saveRole(new RoleEntity(null,"ROLE_MANAGER",null));
			userService.saveRole(new RoleEntity(null,"ROLE_ADMIN",null));
			userService.saveRole(new RoleEntity(null,"ROLE_SUPER_ADMIN",null));

			AppUser donovan = AppUser.builder()
					.name("Donovan Ian")
					.email("donovan@gmail.com")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.build();
			AppUser mia = AppUser.builder()
					.name("Mia Mauren")
					.email("mia@gmail.com")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.build();
			AppUser will = AppUser.builder()
					.name("Will Smith")
					.email("will@gmail.com")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.build();
			AppUser juan = AppUser.builder()
					.name("Juan Manuel")
					.email("jm@gmail.com")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.build();
			userService.saveUser(donovan);
			userService.saveUser(mia);
			userService.saveUser(will);
			userService.saveUser(juan);

			userService.addRoleToUser("donovan@gmail.com","ROLE_USER");
			userService.addRoleToUser("mia@gmail.com","ROLE_MANAGER");
			userService.addRoleToUser("will@gmail.com","ROLE_ADMIN");
			userService.addRoleToUser("jm@gmail.com","ROLE_SUPER_ADMIN");
			userService.addRoleToUser("jm@gmail.com","ROLE_ADMIN");
			userService.addRoleToUser("jm@gmail.com","ROLE_MANAGER");
			userService.addRoleToUser("jm@gmail.com","ROLE_USER");
		};
	}

}
