package com.bexperr.app.ms.items.services;

import java.util.List;
import java.util.Optional;

import com.bexperr.app.ms.items.models.Item;

public interface ItemService {

    List<Item> findAll();
    Optional<Item> findById(Long id);

}
