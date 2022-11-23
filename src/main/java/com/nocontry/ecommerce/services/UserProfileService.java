package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.UserProfileEntity;
import com.nocontry.ecommerce.repositories.UserProfileRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileEntity save(UserProfileEntity user){
        log.info("Saving userProfile entity.");
        return userProfileRepository.saveAndFlush(user);
    }

    public List<UserProfileEntity> findAll(){
        return userProfileRepository.findAll();
    }


}
