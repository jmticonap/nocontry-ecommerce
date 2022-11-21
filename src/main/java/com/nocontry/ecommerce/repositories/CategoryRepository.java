package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

}
