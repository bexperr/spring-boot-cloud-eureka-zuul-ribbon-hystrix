package com.bexperr.app.ms.products.services;

import java.util.List;
import java.util.Optional;

import com.bexperr.app.ms.products.entities.Product;

public interface ProductServiceI {

    List<Product> findAll();
    Optional<Product> findByIdOptional(Long id);

}
