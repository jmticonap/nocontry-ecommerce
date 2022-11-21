package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.PriceEntity;
import com.nocontry.ecommerce.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findByProduct(ProductEntity product);

    PriceEntity getByProductAndIsActive(ProductEntity product, Boolean isActive);

}
