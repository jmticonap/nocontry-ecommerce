package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.CategoryEntity;
import com.nocontry.ecommerce.entities.FeatureEntity;
import com.nocontry.ecommerce.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByCategory(CategoryEntity category);

    List<ProductEntity> findByFeatures(List<FeatureEntity> features);

}
