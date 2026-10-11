package com.bexperr.app.ms.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bexperr.app.ms.items.models.Product;

//@FeignClient(url="localhost:8001", name="ms-products")
@FeignClient(name="ms-products")
public interface ProductFeignClient {

    @GetMapping
    List<Product> finAll();

    @GetMapping("/{id}")
    Product getProduct(@PathVariable Long id);

}
