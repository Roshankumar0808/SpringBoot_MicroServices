package com.ecommerce.microservice.SpringBoot.Inventory.Service.Dto;

import lombok.Data;

import java.util.List;
@Data
public class OrderRequestDto {
    private List<OrderRequestItemDto> items;
}
