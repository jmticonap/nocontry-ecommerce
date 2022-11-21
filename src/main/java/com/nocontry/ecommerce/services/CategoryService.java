package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.dto.CategoryDto;
import com.nocontry.ecommerce.entities.CategoryEntity;
import com.nocontry.ecommerce.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryEntity save(CategoryEntity feature) {
        return categoryRepository.save(feature);
    }

    public CategoryEntity save(CategoryDto categoryDto) {
        return save(CategoryEntity.builder()
                .name(categoryDto.getName())
                .parent(categoryDto.getParent())
                .products(categoryDto.getProducts())
                .build());
    }

    Optional<CategoryEntity> findById(Long id){
        return this.categoryRepository.findById(id);
    }

    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    public CategoryEntity update(Long id,CategoryDto categoryDto) throws Exception {
        Optional<CategoryEntity> category=this.categoryRepository.findById(id);
        if (category.isEmpty()){
            throw new Exception("Id feature no valido");
        }
        category.get().setName(categoryDto.getName());
        category.get().setParent(categoryDto.getParent());
        category.get().setProducts(categoryDto.getProducts());
        return categoryRepository.save(category.get());
    }
    public void delete(Long categoryId) {
        Optional<CategoryEntity> entity= categoryRepository.findById(categoryId);
        try {
            categoryRepository.delete(entity.get());
        } catch (Exception err) {
            log.error(err.getMessage());
        }
    }
}
