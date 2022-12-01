package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.ProductEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired private ProductService productService;

    @Test
    void findById() throws Exception {
        Long id = 1L;
        Optional<ProductEntity> product = productService.findById(id);
        Assertions.assertThat(product.isPresent()).isEqualTo(true);
        Assertions.assertThat(product.get().getId()).isGreaterThan(0);
    }
}