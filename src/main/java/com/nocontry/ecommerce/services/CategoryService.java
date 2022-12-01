package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.CategoryEntity;
import com.nocontry.ecommerce.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public Optional<CategoryEntity> save(CategoryEntity category) {
        return Optional.of(
                categoryRepository.save(category)
        );
    }

    public Optional<CategoryEntity> findById(Long id) {
        Optional<CategoryEntity> ct = categoryRepository.findById(id);
        return ct;
    }

    public CategoryEntity findByName(String name) {
        return categoryRepository.getByName(name);
    }

}
