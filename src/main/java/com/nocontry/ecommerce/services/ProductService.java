package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.ProductEntity;
import com.nocontry.ecommerce.repositories.ProductRepository;
import com.nocontry.ecommerce.dto.ProductTdo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    public ProductEntity save(ProductTdo product) {

        return save(ProductEntity.builder()
                .name(product.getName())
                .description(product.getDescription())
                .build());
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public void delete(ProductEntity product) {
        try {
            productRepository.delete(product);
        } catch (Exception err) {
            log.error(err.getMessage());
        }

    }

}
