package com.nocontry.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase")
public class PurchaseEntity {

    @Id
    @SequenceGenerator(
            name = "purchase_id_sequence",
            sequenceName = "purchase_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "purchase_id_sequence"
    )
    private Long id;
    @OneToOne
    @JoinColumn(name = "cart_id", nullable = false, updatable = false)
    private CartEntity cart;
    @Column(name = "pay_code")
    private String payCode;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date payAt;

}
