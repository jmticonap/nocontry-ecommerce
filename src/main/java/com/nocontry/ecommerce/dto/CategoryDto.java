package com.nocontry.ecommerce.dto;

import com.nocontry.ecommerce.entities.CategoryEntity;
import com.nocontry.ecommerce.entities.ProductEntity;
import lombok.Data;
import java.util.List;

@Data
public class CategoryDto {

    private String name;
    private CategoryEntity parent;
    private List<ProductEntity> products;

}
