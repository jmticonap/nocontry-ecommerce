package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.AppUser;
import com.nocontry.ecommerce.repositories.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

class UserServiceTest {

    /*@Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    private List<AppUser> users = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        users.addAll(List.of(
                AppUser.builder()
                        .name("Ada Haydee")
                        .email("ada.pacheco@gmail.com")
                        .password(new BCryptPasswordEncoder().encode("1234"))
                        .build(),
                AppUser.builder()
                        .name("Godofredo Segundo")
                        .email("segundo@gmail.com")
                        .password(new BCryptPasswordEncoder().encode("1234"))
                        .build(),
                AppUser.builder()
                        .name("Mia Mauren")
                        .email("mia@gmail.com")
                        .password(new BCryptPasswordEncoder().encode("1234"))
                        .build(),
                AppUser.builder()
                        .name("Donovan Ian")
                        .email("donovan@gmail.com")
                        .password(new BCryptPasswordEncoder().encode("1234"))
                        .build()
        ));
    }

    @Test
    void saveUser() {
    }

    @Test
    void getById() {
    }

    @Test
    void getUser() {
    }

    @Test
    void getUsers() {
        Mockito.when(userService.getUsers()).thenReturn(users);

        Assertions.assertNotNull(userService.getUsers());
    }*/
}