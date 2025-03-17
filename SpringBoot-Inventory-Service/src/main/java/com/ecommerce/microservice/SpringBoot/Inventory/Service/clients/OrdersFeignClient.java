package com.ecommerce.microservice.SpringBoot.Inventory.Service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="SpringBoot-Ecommerce-Microservice",path="/orders")
public interface OrdersFeignClient {

    @GetMapping("/core/helloOrders")
    String helloOrders();
}
