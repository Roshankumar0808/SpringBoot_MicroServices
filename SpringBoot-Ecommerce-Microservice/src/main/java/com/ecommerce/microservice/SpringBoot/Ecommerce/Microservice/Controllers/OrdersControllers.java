package com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Controllers;

import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Clients.InventoryOpenFeignClient;
import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Config.FeatureEnableConfig;
import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Dto.OrderRequestDto;
import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Services.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/core")
@RefreshScope
public class OrdersControllers {
    private final OrdersService ordersService;
    private final FeatureEnableConfig featureEnableConfig;



    @Value("${my.variable}")
    private String myvariable;

//    @GetMapping("/helloOrders")
//    public String helloOrders(@RequestHeader("X-User-Id") Long userId){
//
//        return "Hello from Orders Service,user id is: "+userId;
//    }

    @GetMapping("/helloOrders")
    public String helloOrders(){
        if(featureEnableConfig.isUserTrackingEnabled()){
            return "Hello from Orders Service,user variable is: "+  myvariable;
        }
        else{
            return "Hello from Orders Service,feature not available";
        }

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
