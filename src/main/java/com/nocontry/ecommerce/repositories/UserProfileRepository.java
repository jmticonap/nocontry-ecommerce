package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.AppUser;
import com.nocontry.ecommerce.entities.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {

}
