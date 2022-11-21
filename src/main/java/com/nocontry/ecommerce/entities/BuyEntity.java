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
@Table(name = "buy")
public class BuyEntity {

    @Id
    @SequenceGenerator(
            name = "buy_id_sequence",
            sequenceName = "buy_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "buy_id_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private ProductEntity product;
    private Double quantity;
}
