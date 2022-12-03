package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.CartDetailEntity;
import com.nocontry.ecommerce.entities.CartEntity;
import com.nocontry.ecommerce.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CartDetailRepository extends JpaRepository<CartDetailEntity, Long> {

    CartDetailEntity getByCart(CartEntity cart);

    @Transactional
    void deleteByProduct(ProductEntity product);

}
