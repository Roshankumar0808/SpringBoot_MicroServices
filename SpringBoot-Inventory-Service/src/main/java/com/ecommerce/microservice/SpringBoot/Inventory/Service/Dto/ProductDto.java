package com.ecommerce.microservice.SpringBoot.Inventory.Service.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto {
    private Long id;

    private String title;

    private Double price;

    private Integer stock;
}
