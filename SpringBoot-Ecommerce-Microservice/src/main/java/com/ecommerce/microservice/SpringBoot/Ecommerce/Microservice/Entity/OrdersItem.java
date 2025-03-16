package com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders_item")
public class OrdersItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Integer quantity;
   @ManyToOne()
   @JoinColumn(name = "order_id")
    private Orders orders;

}
