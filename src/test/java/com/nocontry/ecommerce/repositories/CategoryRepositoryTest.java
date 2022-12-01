package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.CategoryEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired private CategoryRepository categoryRepository;

    @Test
    void getReferenceById(){
        Long id = 1L;
        CategoryEntity category = categoryRepository.getReferenceById(id);
        Assertions.assertThat(category.getId()).isEqualTo(id);
    }

}