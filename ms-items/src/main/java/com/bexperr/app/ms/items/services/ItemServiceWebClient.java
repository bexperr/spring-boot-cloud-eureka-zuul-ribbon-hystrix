package com.bexperr.app.ms.items.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.bexperr.app.ms.items.models.Item;
import com.bexperr.app.ms.items.models.Product;

@Primary 
@Service 
public class ItemServiceWebClient implements ItemService{

    @Value("${config.base.url.api.ms-products}")
    String URL_API;

    private final WebClient.Builder client;
  

    public ItemServiceWebClient(Builder client) {
        this.client = client;
    }

    @Override
    public List<Item> findAll() {
        return this.client.build()
        .get()
        .uri(URL_API)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(Product.class)
        .map(product -> new Item(product, new Random().nextInt(10)+1))
        .collectList()
        .block();
    }

    @Override
    public Optional<Item> findById(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);

        try {
            return client.build()
                    .get()
                    .uri(URL_API+"/{id}", params)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Product.class)
                    .map(product -> new Item(product, new Random().nextInt(10) + 1))
                    .blockOptional();
        } catch (WebClientException e) {
            return Optional.empty();
        }
    }

}
