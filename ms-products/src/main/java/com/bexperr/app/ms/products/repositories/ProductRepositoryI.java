package com.bexperr.app.ms.products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bexperr.app.ms.products.entities.Product;

public interface ProductRepositoryI extends CrudRepository<Product,Long>{

}
