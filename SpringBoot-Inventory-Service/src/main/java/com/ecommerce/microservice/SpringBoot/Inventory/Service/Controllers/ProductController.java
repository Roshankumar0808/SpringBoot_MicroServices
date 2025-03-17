package com.ecommerce.microservice.SpringBoot.Inventory.Service.Controllers;

import com.ecommerce.microservice.SpringBoot.Inventory.Service.Dto.OrderRequestDto;
import com.ecommerce.microservice.SpringBoot.Inventory.Service.Dto.ProductDto;
import com.ecommerce.microservice.SpringBoot.Inventory.Service.Services.ProductService;
import com.ecommerce.microservice.SpringBoot.Inventory.Service.clients.OrdersFeignClient;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;
    private final OrdersFeignClient ordersFeignClient;
    @GetMapping("/fetchOrders")
    public String fetchFromOrderService(HttpServletRequest httpServletRequest){
        log.info(httpServletRequest.getHeader("x-custom-header"));
      //  ServiceInstance orderService=discoveryClient.getInstances("SpringBoot-Ecommerce-Microservice").getFirst();
       return ordersFeignClient.helloOrders();
        // return restClient.get().uri(orderService.getUri()+"/orders/core/helloOrders").retrieve().body(String.class);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>>getAllInventory(){
        List<ProductDto>productDtos=productService.getAllInventory();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto>getInventoryById(@PathVariable Long id){
       ProductDto productDto=productService.getProductById(id);
       return  ResponseEntity.ok(productDto);
    }

    @PutMapping("/reduce-stocks")
    public ResponseEntity<Double>reduceStocks(@RequestBody OrderRequestDto orderRequestDto){
        Double totalPrice=productService.reduceStocks(orderRequestDto);
        return ResponseEntity.ok(totalPrice);
    }

}
