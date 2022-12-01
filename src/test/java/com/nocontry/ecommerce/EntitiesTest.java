package com.nocontry.ecommerce;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocontry.ecommerce.entities.CategoryEntity;
import com.nocontry.ecommerce.entities.ProductEntity;
import com.nocontry.ecommerce.services.CategoryService;
import com.nocontry.ecommerce.services.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EntitiesTest {

    @Autowired private ProductService productService;
    @Autowired private CategoryService categoryService;

    @Test
    void Product_creating_and_marshelling() throws Exception {
        CategoryEntity category = categoryService.findByName("tech");
        ProductEntity product = ProductEntity
                .builder()
                .name("Producto 1")
                .description("Descripción")
                .category(category)
                .build();
        productService.save(product);

        String result = new ObjectMapper().writeValueAsString(product);

        Assertions.assertThat(product).isInstanceOf(Exception.class);
    }

}
