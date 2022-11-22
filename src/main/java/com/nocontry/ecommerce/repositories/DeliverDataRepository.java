package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.CartEntity;
import com.nocontry.ecommerce.entities.DeliverDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliverDataRepository extends JpaRepository<DeliverDataEntity, Long> {

    DeliverDataEntity getByCart(CartEntity cart);

}
