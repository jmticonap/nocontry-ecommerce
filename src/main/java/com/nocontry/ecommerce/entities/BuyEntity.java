package com.nocontry.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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
    private ProductEntity product;

    @Column(name = "quantity", nullable = false, updatable = false)
    private Double quantity;
}
