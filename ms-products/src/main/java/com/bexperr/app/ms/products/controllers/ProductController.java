package com.bexperr.app.ms.products.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.bexperr.app.ms.products.entities.Product;
import com.bexperr.app.ms.products.services.ProductServiceI;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ProductController {

    final ProductServiceI productService;

    public ProductController(ProductServiceI productService) {
        this.productService = productService;
    }

    
    @GetMapping
    public List<Product> list() {
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) throws InterruptedException{

        //inicio generacion de fallos
        if(id.equals(10L)){
            throw new IllegalStateException("Producto no encontrado");
        }

         if(id.equals(7L)){
            TimeUnit.SECONDS.sleep(5);
        }
        //termina generacion de fallos

        Optional<Product> productOptional = this.productService.findByIdOptional(id);
        
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    

}
