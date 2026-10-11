package com.bexperr.app.ms.products.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bexperr.app.ms.products.entities.Product;
import com.bexperr.app.ms.products.repositories.ProductRepositoryI;


@Service 
public class ProductServiceImpl implements ProductServiceI{

    final private ProductRepositoryI repository;
    final private Environment environment;

    ProductServiceImpl(ProductRepositoryI repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @Override
    @Transactional (readOnly = true)
    public List<Product> findAll() {
        return ((List<Product>) repository.findAll()).stream().map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Product> findByIdOptional(Long id) {
        return repository.findById(id);
    }

}
