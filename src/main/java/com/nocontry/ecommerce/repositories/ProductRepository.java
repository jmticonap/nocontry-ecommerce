package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.CategoryEntity;
import com.nocontry.ecommerce.entities.FeatureEntity;
import com.nocontry.ecommerce.entities.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    /**
     *
     * @param category
     * @return List<ProductEntity>
     */
    List<ProductEntity> findByCategory(CategoryEntity category, Pageable pageable);

    /**
     *
     * @param features
     * @return List<ProductEntity>
     */
    List<ProductEntity> findByFeatures(FeatureEntity[] features);

}
