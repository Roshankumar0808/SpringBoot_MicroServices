package com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Clients;

import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Dto.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SpringBoot-Inventory-Service",path = "/inventory")
public interface InventoryOpenFeignClient {

    @PutMapping("/products/reduce-stocks")
    Double reduceStocks(@RequestBody OrderRequestDto orderRequestDto);
}
