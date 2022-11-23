package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {

    Optional<FeatureEntity> getByName(String name);

}
