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
@Table(name = "deliver_data")
public class DeliverDataEntity {

    @Id
    @SequenceGenerator(
            name = "deliver_id_sequence",
            sequenceName = "deliver_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "deliver_id_sequence"
    )
    private Long id;

    @Column(nullable = true)
    private String country;

    private String state;

    private String city;

    private String street;

    @Column(name = "postal_code")
    private String postalCode;

    @OneToOne
    @JoinColumn(name = "cart_id", nullable = false, updatable = false)
    private CartEntity cart;



}
