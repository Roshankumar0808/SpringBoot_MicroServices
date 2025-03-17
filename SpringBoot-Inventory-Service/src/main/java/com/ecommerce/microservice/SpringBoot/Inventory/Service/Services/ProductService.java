package com.ecommerce.microservice.SpringBoot.Inventory.Service.Services;

import com.ecommerce.microservice.SpringBoot.Inventory.Service.Dto.OrderRequestDto;
import com.ecommerce.microservice.SpringBoot.Inventory.Service.Dto.OrderRequestItemDto;
import com.ecommerce.microservice.SpringBoot.Inventory.Service.Dto.ProductDto;
import com.ecommerce.microservice.SpringBoot.Inventory.Service.Entity.Product;
import com.ecommerce.microservice.SpringBoot.Inventory.Service.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto>getAllInventory(){
        log.info("Fetching All Iventory Items");

        List<Product> products=productRepository.findAll();
        return products.stream().map(product -> modelMapper.map(product, ProductDto.class)).toList();
    }

    public ProductDto getProductById(Long id){
       log.info("Fetching product with id:{}",id);

        Optional<Product> product=productRepository.findById(id);
        return product.map(product1 -> modelMapper.map(product1, ProductDto.class)).orElseThrow(()->{
            return new RuntimeException("Fetching product with given id:"+id);
        });

    }
   @Transactional
    public Double reduceStocks(OrderRequestDto orderRequestDto) {
        log.info("Reducing the stocks");
        Double totalPrice=0.0;
        for(OrderRequestItemDto orderRequestItemDto:orderRequestDto.getItems()){
             Long productId= orderRequestItemDto.getProductId();
             Integer quantity=orderRequestItemDto.getQuantity();
             Product product=productRepository.findById(productId).orElseThrow(()->
                  new RuntimeException("Product Not Found with Id:"+productId)
             );
             if(product.getStock()<quantity){
                 throw new RuntimeException("Product cannot be fulfilled for given quantity");
             }
             product.setStock(product.getStock()-quantity);
             productRepository.save(product);
             totalPrice+=quantity*product.getPrice();
        }
        return totalPrice;
    }
}
