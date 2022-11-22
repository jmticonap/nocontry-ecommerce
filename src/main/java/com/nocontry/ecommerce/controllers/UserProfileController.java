package com.nocontry.ecommerce.controllers;

import com.nocontry.ecommerce.dto.UserProfileDto;
import com.nocontry.ecommerce.entities.UserProfileEntity;
import com.nocontry.ecommerce.services.UserProfileService;
import com.nocontry.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final UserService userService;

    /**
     *
     * @param user
     * @return UserProfileEntity
     */
    @PostMapping
    public ResponseEntity<UserProfileEntity> save(@RequestBody UserProfileDto user) {
        return new ResponseEntity<>(userProfileService.save(
                UserProfileEntity.builder()
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .build()),
                HttpStatus.OK
        );
    }

    /**
     *
     * @return List<UserProfileEntity>
     */
    @GetMapping
    public ResponseEntity<List<UserProfileEntity>> findAll(){
        return new ResponseEntity<>(userProfileService.findAll(), HttpStatus.OK);
    }

}
