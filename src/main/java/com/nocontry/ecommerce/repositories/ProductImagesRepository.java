package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.ProductEntity;
import com.nocontry.ecommerce.entities.ProductImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductImagesRepository extends JpaRepository<ProductImagesEntity, Long> {

    List<ProductImagesEntity> findByProduct(ProductEntity product);

}
