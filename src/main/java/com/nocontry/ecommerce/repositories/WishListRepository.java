package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.UserProfileEntity;
import com.nocontry.ecommerce.entities.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishListEntity, Long> {

    List<WishListEntity> findByUser(UserProfileEntity user);

}
