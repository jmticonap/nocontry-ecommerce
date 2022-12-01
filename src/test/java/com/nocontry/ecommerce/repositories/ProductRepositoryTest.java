package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.ProductEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void getReferenceById() {
        ProductEntity product = productRepository.getReferenceById(1L);
        Assertions.assertThat(product.getId()).isNotNull();
    }

}