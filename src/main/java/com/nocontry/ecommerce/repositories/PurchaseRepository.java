package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.CartEntity;
import com.nocontry.ecommerce.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    List<PurchaseEntity> getByCart(CartEntity cart);

}
