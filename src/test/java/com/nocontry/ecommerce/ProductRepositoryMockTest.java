package com.nocontry.ecommerce;

import com.nocontry.ecommerce.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired private ProductRepository productRepository;

    @Test
    void injectedComponentsAreNotNull(){
        Assertions.assertThat(productRepository).isNotNull();
    }


}
