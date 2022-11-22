package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.AppUser;
import com.nocontry.ecommerce.entities.Role;
import com.nocontry.ecommerce.repositories.RoleRepo;
import com.nocontry.ecommerce.repositories.UserRepo;
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

    private UserRepo userRepo;
    private RoleRepo roleRepo;

    public AppUser saveUser(AppUser user){
        log.info("Saving new user into DB");
        return userRepo.save(user);
    }

    public Role saveRole(Role role){
        log.info("Saving new role into DB");
        return roleRepo.save(role);
    }

    public void addRoleToUser(String email, String rolename){
        log.info("Adding role:{} into user:{}", rolename, email);
        AppUser user = null;
        if(userRepo.existsByEmail(email)){
            user = userRepo.findByEmail(email).get();
        }

        Role role = roleRepo.findByName(rolename);
        user.getRoles().add(role); // for @Transactional annotation this save into DB
    }

    public AppUser getById(Long id){
        log.info("Getting user data.");
        return userRepo.getReferenceById(id);
    }

    public AppUser getUser(String email){
        log.info("Getting user:{}", email);
        return userRepo.findByEmail(email).get();
    }

    public List<AppUser> getUsers(){
        log.info("Getting all users");
        return userRepo.findAll();
    }

}
