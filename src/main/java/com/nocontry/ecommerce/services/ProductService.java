package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.*;
import com.nocontry.ecommerce.repositories.*;
import com.nocontry.ecommerce.dto.ProductTdo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final FeatureRepository featureRepository;
    @Autowired
    private final ProductImagesRepository productImagesRepository;
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final PriceRepository priceRepository;


    private void putActivePrice(ProductEntity product) {
        PriceEntity activePrice = priceRepository.findOneByProductAndIsActiveTrue(product);
        if (activePrice != null && activePrice.getValue() > 0)
            product.setPrice(activePrice.getValue());
    }

    public Optional<ProductEntity> save(ProductEntity product) throws Exception {
        /*if(product.getId() != null)
            throw new Exception("The product exist, for update fields values user <<update>>");*/
        try {
            Optional<CategoryEntity> category = categoryRepository.findById(product.getCategory().getId());
        } catch (Exception err) {
            log.error("Category not found for product: {}",product.getName());
            product.setCategory(null);
        }

        //********************************************************
        ProductEntity newProduct = null;
        try{
            newProduct = productRepository.save(product);
        } catch (Exception err) {
            log.error(err.getMessage());
            throw new Exception(err.getMessage());
        }


        if (product.getPrice() != null && product.getPrice() > 0) {
            PriceEntity activePrice = priceRepository.findOneByProductAndIsActiveTrue(product);
            if (activePrice != null && activePrice.getValue() != product.getPrice()) {
                activePrice.setIsActive(false);
                priceRepository.save(activePrice);
                priceRepository.save(PriceEntity.builder()
                        .value(product.getPrice())
                        .product(newProduct)
                        .build());
            } else {
                priceRepository.save(PriceEntity.builder()
                        .value(product.getPrice())
                        .product(product)
                        .build());
            }
        }

        if (product.getImages() != null && product.getImages().size() > 0) {
            ProductEntity finalNewProduct = newProduct;
            product.getImages().stream().forEach(image -> {
                image.setProduct(finalNewProduct);
                productImagesRepository.save(image);
            });
        }

        return Optional.of(newProduct);
    }

    public ProductEntity save(ProductTdo product) throws Exception {

        return save(ProductEntity.builder()
                .name(product.getName())
                .description(product.getDescription())
                .build()).get();
    }

    public Page<ProductEntity> findAll(Pageable pageable) {
        Page<ProductEntity> products = productRepository.findAll(pageable);
        products.stream().forEach(product -> putActivePrice(product));
        return products;
    }

    public Optional<ProductEntity> findById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        putActivePrice(product.get());
        return product;
    }

    public List<ProductEntity> findByCategory(CategoryEntity category, Pageable pageable) {
        List<ProductEntity> products = productRepository.findByCategory(category, pageable);
        products.stream().forEach(product -> putActivePrice(product));
        return products;
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
