package com.nocontry.ecommerce;

import com.nocontry.ecommerce.entities.*;
import com.nocontry.ecommerce.repositories.CategoryRepository;
import com.nocontry.ecommerce.services.FeatureService;
import com.nocontry.ecommerce.services.ProductService;
import com.nocontry.ecommerce.services.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Apply Default Global SecurityScheme in springdoc-openapi",
                version = "1.0.0"
        ),
        security = {@SecurityRequirement(name = "api_key")}
)
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(
            UserService userService,
            CategoryRepository categoryRepository,
            ProductService productService,
            FeatureService featureService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

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

            userService.addRoleToUser("donovan@gmail.com", "ROLE_USER");
            userService.addRoleToUser("mia@gmail.com", "ROLE_MANAGER");
            userService.addRoleToUser("will@gmail.com", "ROLE_ADMIN");
            userService.addRoleToUser("jm@gmail.com", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("jm@gmail.com", "ROLE_ADMIN");
            userService.addRoleToUser("jm@gmail.com", "ROLE_MANAGER");
            userService.addRoleToUser("jm@gmail.com", "ROLE_USER");

            /**
             *
             */
            categoryRepository.save(
                    CategoryEntity.builder()
                            .name("Generic")
                            .build()
            );
            categoryRepository.save(
                    CategoryEntity.builder()
                            .name("audio")
                            .build()
            );
            categoryRepository.save(
                    CategoryEntity.builder()
                            .name("tech")
                            .build()
            );


            ProductEntity product1 = productService.save(
                    ProductEntity.builder()
                            .name("Lenovo ideapad 5")
                            .description("Laptop ideapad 5 core i7")
                            .category(categoryRepository.getByName("tech"))
                            .build()
            );
            productService.save(
                    ProductEntity.builder()
                            .name("Ipad Prod")
                            .description("Ipad pro gray")
                            .category(categoryRepository.getByName("tech"))
                            .build()
            );
            productService.save(
                    ProductEntity.builder()
                            .name("Xiaomi Redmi 8")
                            .description("Xiaomi Redmi 8")
                            .category(categoryRepository.getByName("tech"))
                            .build()
            );
            productService.save(
                    ProductEntity.builder()
                            .name("Mac Book Pro")
                            .description("Mac Book Pro M1 17\"")
                            .build()
            );

            FeatureEntity feature1 = featureService.save(FeatureEntity.builder()
                    .name("intel core i7")
                    .build());
            featureService.save(FeatureEntity.builder()
                    .name("Processor M1")
                    .build());

            if(product1.getFeatures() == null){
                List<FeatureEntity> features = new ArrayList<>();
                features.add(feature1);
                product1.setFeatures(features);
            } else {
                product1.getFeatures().add(feature1);
            }

            productService.save(product1);

        };
    }

}
