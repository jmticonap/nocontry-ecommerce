package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.ProductImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImagesRepository extends JpaRepository<ProductImagesEntity, Long> {
}
