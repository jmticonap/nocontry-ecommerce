package com.nocontry.ecommerce.marshelling;

import com.nocontry.ecommerce.entities.ProductEntity;

import java.util.HashMap;
import java.util.Map;

public class ProductMarsheller {

    public static Map<String, Object> marshall(ProductEntity product){
        Map<String, Object> result = new HashMap<>();
        result.put("id", product.getId());
        result.put("name",product.getName());
        result.put("description",product.getDescription());
        result.put("images",product.getImages().stream().map(image -> {
            Map<String, Object> img = new HashMap<>();
            img.put("id", image.getId());
            img.put("path", image.getPath());
            return img;
        }));
        Map<String, Object> cat = new HashMap<>();
        cat.put("id", product.getCategory().getId());
        cat.put("name", product.getCategory().getName());
        result.put("category",cat);

        return result;
    }

}
