package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.BuyEntity;
import com.nocontry.ecommerce.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyRepository extends JpaRepository<BuyEntity, Long> {

    List<BuyEntity> findByProduct(ProductEntity product);

}
