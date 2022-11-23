package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.ProductImagesEntity;
import com.nocontry.ecommerce.repositories.ProductImagesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductImagesService {

    private final ProductImagesRepository productImagesRepository;

    public ProductImagesEntity save(ProductImagesEntity productImages) throws Exception {
        try {
            return productImagesRepository.save(productImages);
        } catch (Exception err) {
            log.error(err.getMessage());
            throw new Exception(err.getMessage());
        }
    }

}
