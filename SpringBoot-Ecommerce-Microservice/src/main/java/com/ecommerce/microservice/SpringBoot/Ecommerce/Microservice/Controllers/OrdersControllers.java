package com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Controllers;

import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Clients.InventoryOpenFeignClient;
import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Dto.OrderRequestDto;
import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Services.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/core")
public class OrdersControllers {
    private final OrdersService ordersService;


    @GetMapping("/helloOrders")
    public String helloOrders(){

        return "Hello from Orders Service";
    }

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllInventory(){
        List<OrderRequestDto>orders=ordersService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

     @PostMapping("create-order")
     public ResponseEntity<OrderRequestDto>createOrder(@RequestBody OrderRequestDto orderRequestDto){
       OrderRequestDto orderRequestDto1=ordersService.createOrder(orderRequestDto);
       return ResponseEntity.ok(orderRequestDto1);
     }
    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto>getInventoryById(@PathVariable Long id){
        OrderRequestDto orders=ordersService.getOrderById(id);
        return  ResponseEntity.ok(orders);
    }
}
