package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.CategoryEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    @Autowired private CategoryService categoryService;

    @Test
    void findById() throws Exception {
        Long id = 1L;
        Optional<CategoryEntity> category = categoryService.findById(id);
        Assertions.assertThat(category.get().getId()).isEqualTo(id);
    }

}