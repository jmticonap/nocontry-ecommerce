package com.nocontry.ecommerce.dto;

import com.nocontry.ecommerce.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends ProductEntity {

    private Long categoryId;

}
