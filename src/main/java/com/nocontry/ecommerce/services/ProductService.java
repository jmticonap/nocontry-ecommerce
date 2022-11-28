package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.FeatureEntity;
import com.nocontry.ecommerce.entities.ProductEntity;
import com.nocontry.ecommerce.entities.ProductImagesEntity;
import com.nocontry.ecommerce.repositories.CategoryRepository;
import com.nocontry.ecommerce.repositories.FeatureRepository;
import com.nocontry.ecommerce.repositories.ProductImagesRepository;
import com.nocontry.ecommerce.repositories.ProductRepository;
import com.nocontry.ecommerce.dto.ProductTdo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final FeatureRepository featureRepository;
    private final ProductImagesRepository productImagesRepository;
    private final CategoryRepository categoryRepository;

    public ProductEntity save(ProductEntity product) throws Exception {
        try {
            return productRepository.save(product);
        } catch (Exception err) {
            log.error(err.getMessage());
            throw new Exception(err.getMessage());
        }
    }

    public ProductEntity save(ProductTdo product) throws Exception {

        return save(ProductEntity.builder()
                .name(product.getName())
                .description(product.getDescription())
                .build());
    }

    public List<ProductEntity> findAll() {
        List<ProductEntity> products = productRepository.findAll();
        //TODO: load category data
        return products;
    }

    public ProductEntity findById(Long id) throws Exception {
        try {
            return productRepository.getReferenceById(id);
        } catch (Exception err) {
            log.error(err.getMessage());
            throw new Exception(err.getMessage());
        }
    }

    public void delete(ProductEntity product) {
        try {
            productRepository.delete(product);
        } catch (Exception err) {
            log.error(err.getMessage());
        }

    }


    public ProductEntity addImages(Long productId, List<Long> imagesIds) throws Exception {
        try {
            ProductEntity product = productRepository.getReferenceById(productId);
            List<ProductImagesEntity> images = new ArrayList<>();
            imagesIds.stream().forEach((id) -> {
                images.add(productImagesRepository.getReferenceById(id));
            });
            product.getImages().addAll(images);

            return save(product);
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

            return save(product);
        } catch (Exception err) {
            log.error(err.getMessage());
            throw new Exception(err.getMessage());
        }
    }

}
