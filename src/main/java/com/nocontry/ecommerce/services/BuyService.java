package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.BuyEntity;
import com.nocontry.ecommerce.entities.ProductEntity;
import com.nocontry.ecommerce.repositories.BuyRepository;
import com.nocontry.ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BuyService {

    @Autowired
    private final BuyRepository buyRepository;
    @Autowired
    private final ProductRepository productRepository;

    public List<BuyEntity> save(List<Map<String, Number>> data) {
        List<BuyEntity> result = new ArrayList<>();
        data.stream().forEach(d -> {
            Long productId = d.get("productId").longValue();
            Optional<ProductEntity> product = productRepository.findById(productId);
            if (product.isPresent()) {
                BuyEntity buy = buyRepository.save(
                        BuyEntity.builder()
                                .product(product.get())
                                .quantity(d.get("quantity").doubleValue())
                                .build()
                );

                product.get().setStock( product.get().getStock() + d.get("quantity").doubleValue());
                productRepository.save(product.get());

                result.add(buy);
            }
        });
        return result;
    }
}
