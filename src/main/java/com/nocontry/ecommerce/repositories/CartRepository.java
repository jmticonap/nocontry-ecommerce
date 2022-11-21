package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
