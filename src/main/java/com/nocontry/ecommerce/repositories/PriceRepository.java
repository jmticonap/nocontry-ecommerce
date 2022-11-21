package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
}
