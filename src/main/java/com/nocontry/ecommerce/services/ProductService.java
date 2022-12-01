package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.CategoryEntity;
import com.nocontry.ecommerce.entities.FeatureEntity;
import com.nocontry.ecommerce.entities.ProductEntity;
import com.nocontry.ecommerce.entities.ProductImagesEntity;
import com.nocontry.ecommerce.repositories.CategoryRepository;
import com.nocontry.ecommerce.repositories.FeatureRepository;
import com.nocontry.ecommerce.repositories.ProductImagesRepository;
import com.nocontry.ecommerce.repositories.ProductRepository;
import com.nocontry.ecommerce.dto.ProductTdo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final FeatureRepository featureRepository;
    private final ProductImagesRepository productImagesRepository;
    private final CategoryRepository categoryRepository;

    public Optional<ProductEntity> save(ProductEntity product) {
        return Optional.of(productRepository.save(product));
    }

    public ProductEntity save(ProductTdo product) throws Exception {

        return save(ProductEntity.builder()
                .name(product.getName())
                .description(product.getDescription())
                .build()).get();
    }

    public List<ProductEntity> findAll(Pageable pageable) {
        List<ProductEntity> products = productRepository.findAll(pageable).toList();

        return products;
    }

    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<ProductEntity> findByCategory(CategoryEntity category, Pageable pageable){
        return productRepository.findByCategory(category, pageable);
    }

    public void delete(ProductEntity product) {
        productRepository.delete(product);
    }


    public ProductEntity addImages(Long productId, List<Long> imagesIds) throws Exception {
        try {
            ProductEntity product = productRepository.getReferenceById(productId);
            List<ProductImagesEntity> images = new ArrayList<>();
            imagesIds.stream().forEach((id) -> {
                images.add(productImagesRepository.getReferenceById(id));
            });
            product.getImages().addAll(images);

            return save(product).get();
        } catch (Exception err) {
            log.error(err.getMessage());
            throw new Exception(err.getMessage());
        }
    }

    public ProductEntity addFeatures(Long productId, List<Long> featuresIds) throws Exception {
        try {
            ProductEntity product = productRepository.getReferenceById(productId);
            List<FeatureEntity> features = new ArrayList<>();
            featuresIds.stream().forEach((id) -> {
                features.add(featureRepository.getReferenceById(id));
            });
            product.getFeatures().addAll(features);

            return save(product).get();
        } catch (Exception err) {
            log.error(err.getMessage());
            throw new Exception(err.getMessage());
        }
    }

}
