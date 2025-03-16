package com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Repository;

import com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepo extends JpaRepository<Orders,Long> {

}
