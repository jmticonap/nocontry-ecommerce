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
@Table(name = "cart_detail")
public class CartDetailEntity {

    @Id
    @SequenceGenerator(
            name = "cart_detail_id_sequence",
            sequenceName = "cart_detail_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_detail_id_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false, updatable = false)
    private CartEntity cart;
    private Double quantity;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private ProductEntity product;
    private Double price;
    
}
