package com.bexperr.app.ms.items.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Service;

import com.bexperr.app.ms.items.clients.ProductFeignClient;
import com.bexperr.app.ms.items.models.Item;
import com.bexperr.app.ms.items.models.Product;

import feign.FeignException;

import java.util.stream.Collectors;

@Service 
public class ItemServiceFeign implements ItemService{

    private final ProductFeignClient client;

    ItemServiceFeign(ProductFeignClient client) {
        this.client = client;
    }

    @Override
    public List<Item> findAll() {
        return client.finAll().stream().map(product -> {

            Random random = new Random();

            return new Item(product,random.nextInt(10)+1);

        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findById(Long id) {
        try {
             Product product = client.getProduct(id);
             Random random = new Random();
             return Optional.of(new Item(product,random.nextInt(10)+1));
        } catch (FeignException e) {
            return Optional.empty();
        }
    }

}
