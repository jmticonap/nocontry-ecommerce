package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.CartDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetailEntity,Long> {

}
