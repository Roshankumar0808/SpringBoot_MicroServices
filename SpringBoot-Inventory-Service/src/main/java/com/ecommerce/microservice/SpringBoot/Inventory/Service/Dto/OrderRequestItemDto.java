package com.ecommerce.microservice.SpringBoot.Inventory.Service.Dto;

import lombok.Data;

@Data
public class OrderRequestItemDto {
    private Long productId;
    private Integer quantity;


}
