package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository  extends JpaRepository<PurchaseEntity,Long> {
}
