package com.nocontry.ecommerce.repositories;

import com.nocontry.ecommerce.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);

}
