package com.bexperr.app.ms.items.controllers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bexperr.app.ms.items.models.Item;
import com.bexperr.app.ms.items.services.ItemService;

@RestController
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<Item> list(){
        return service.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Item> details(@PathVariable Long id) {
        Optional<Item> itemTemp = service.findById(id);
        if (itemTemp.isPresent()) {
            return ResponseEntity.ok(itemTemp.get());
        }
        return ResponseEntity.notFound().build();
    }

}
