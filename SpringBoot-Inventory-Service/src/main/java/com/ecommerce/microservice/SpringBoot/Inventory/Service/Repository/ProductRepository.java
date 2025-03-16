package com.ecommerce.microservice.SpringBoot.Inventory.Service.Repository;

import com.ecommerce.microservice.SpringBoot.Inventory.Service.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
