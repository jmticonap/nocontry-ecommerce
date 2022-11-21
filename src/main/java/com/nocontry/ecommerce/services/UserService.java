package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.AppUser;
import com.nocontry.ecommerce.entities.RoleEntity;
import com.nocontry.ecommerce.repositories.RoleRepository;
import com.nocontry.ecommerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private UserRepository userRepo;
    private RoleRepository roleRepo;

    public AppUser saveUser(AppUser user){
        log.info("Saving new user into DB");
        return userRepo.save(user);
    }

    public RoleEntity saveRole(RoleEntity roleEntity){
        log.info("Saving new roleEntity into DB");
        return roleRepo.save(roleEntity);
    }

    public void addRoleToUser(String email, String rolename){
        log.info("Adding roleEntity:{} into user:{}", rolename, email);
        AppUser user = null;
        if(userRepo.existsByEmail(email)){
            user = userRepo.findByEmail(email).get();
        }

        RoleEntity roleEntity = roleRepo.findByName(rolename);
        user.getRoleEntities().add(roleEntity); // for @Transactional annotation this save into DB
    }

    public AppUser getUser(String username){
        log.info("Getting user:{}", username);
        return userRepo.findByEmail(username).get();
    }

    public List<AppUser> getUsers(){
        log.info("Getting all users");
        return userRepo.findAll();
    }

}
