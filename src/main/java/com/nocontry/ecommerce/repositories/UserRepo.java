package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
    Boolean existsByEmail(String email);

}
