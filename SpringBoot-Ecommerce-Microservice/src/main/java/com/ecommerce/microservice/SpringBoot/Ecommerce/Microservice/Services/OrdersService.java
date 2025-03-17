package com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Services;

import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Clients.InventoryOpenFeignClient;
import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Dto.OrderRequestDto;
import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Entity.Orders;
import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Entity.OrdersItem;
import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Entity.OrdersStatusEnum;
import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Repository.OrdersRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {
    private final OrdersRepo ordersRepo;
    private final ModelMapper modelMapper;
    private final InventoryOpenFeignClient inventoryOpenFeignClient;

    public List<OrderRequestDto> getAllOrders(){
        log.info("Fetching All orders");

        List<Orders> orders=ordersRepo.findAll();
        return orders.stream().map(product -> modelMapper.map(product, OrderRequestDto.class)).toList();
    }

    public OrderRequestDto getOrderById(Long id){
        log.info("Fetching order with id:{}",id);
        Optional<Orders> orders=ordersRepo.findById(id);
        return orders.map(product1 -> modelMapper.map(product1, OrderRequestDto.class)).orElseThrow(()->{
            return new RuntimeException("Fetching orders with given id:"+id);
        });

    }

    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {
        Double totalPrice= inventoryOpenFeignClient.reduceStocks(orderRequestDto);
        Orders orders=modelMapper.map(orderRequestDto, Orders.class);
        for(OrdersItem ordersItem:orders.getItems()){
            ordersItem.setOrders(orders);
        }
        orders.setTotalprice(totalPrice);
        orders.setOrdersStatusEnum(OrdersStatusEnum.CONFIRMED);
        Orders savedOrder=ordersRepo.save(orders);
        return modelMapper.map(savedOrder, OrderRequestDto.class);
    }
}
