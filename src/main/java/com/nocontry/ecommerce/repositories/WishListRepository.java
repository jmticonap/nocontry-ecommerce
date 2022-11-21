package com.nocontry.ecommerce.repositories;
import com.nocontry.ecommerce.entities.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishListEntity, Long> {
}

