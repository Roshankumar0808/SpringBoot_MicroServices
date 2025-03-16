package com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Controllers;

import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Dto.OrderRequestDto;
import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Services.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersControllers {
    private final OrdersService ordersService;
    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllInventory(){
        List<OrderRequestDto>orders=ordersService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto>getInventoryById(@PathVariable Long id){
        OrderRequestDto orders=ordersService.getOrderById(id);
        return  ResponseEntity.ok(orders);
    }
}
