package com.nocontry.ecommerce.dto;

import com.nocontry.ecommerce.entities.ProductEntity;
import lombok.Data;
import java.util.List;

@Data
public class FeatureDto {

    private String name;
    private List<ProductEntity> products;
}
