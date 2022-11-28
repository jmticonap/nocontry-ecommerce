package com.nocontry.ecommerce;

import com.nocontry.ecommerce.entities.AppUser;
import com.nocontry.ecommerce.repositories.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class EcommerceApplicationTests {

    /*@Autowired
    private UserRepo userRepo;

    @Test
    void contextLoads() {
        List<AppUser> users = userRepo.findAll();
        Assertions.assertThat(users).hasSize(5);
    }

    @Test
    void findUserByEmail() {
        String requireEmail = "donovan@gmail.com";
        AppUser donovan = userRepo.findByEmail(requireEmail).get();
        Assertions.assertThat(donovan.getEmail()).isEqualTo(requireEmail);
    }


    @Test
    void saveNewUser() {
        AppUser ada = AppUser.builder()
                .name("Ada Haydee")
                .email("ada.pacheco@gmail.com")
                .password(new BCryptPasswordEncoder().encode("1234"))
                .build();
        ada = userRepo.save(ada);

        Assertions.assertThat(ada.getId()).isGreaterThan(0);
        Assertions.assertThat(ada.getId()).isNotNull();
    }*/

}