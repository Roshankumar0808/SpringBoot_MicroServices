package com.ecommerce.microservice.SpringBoot.Inventory.Service.Controllers;

import com.ecommerce.microservice.SpringBoot.Inventory.Service.Dto.ProductDto;
import com.ecommerce.microservice.SpringBoot.Inventory.Service.Services.ProductService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

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

}
