package com.bexperr.items.service;
import java.util.List;

import com.bexperr.items.models.*;

public interface IItemService {
	
	public List<Item> findAll();
	public Item findById(Long id, Integer cantidad);

}
