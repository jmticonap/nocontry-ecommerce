package com.nocontry.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wish_list")
public class WishListEntity {

    @Id
    @SequenceGenerator(
            name = "wish_list_id_sequence",
            sequenceName = "wish_list_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wish_list_id_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private UserProfileEntity user;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private ProductEntity product;

}
