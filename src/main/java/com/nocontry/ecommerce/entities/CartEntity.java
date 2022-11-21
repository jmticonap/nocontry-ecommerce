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
@Table(name = "cart")
public class CartEntity {

    @Id
    @SequenceGenerator(
            name = "cart_id_sequence",
            sequenceName = "cart_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_id_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private UserProfileEntity user;
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    private Boolean isPurchased;
    @Transient
    private Double total;
    @OneToOne(mappedBy = "cart")
    private DeliverDataEntity deliverData;
    @OneToOne(mappedBy = "cart")
    private PurchaseEntity purchase;
    
}
