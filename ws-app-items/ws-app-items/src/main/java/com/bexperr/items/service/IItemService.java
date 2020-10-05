package com.bexperr.items.service;
import java.util.List;

import com.bexperr.items.models.Item;
import com.bexperr.commons.models.Producto;

public interface IItemService {
	
	public List<Item> findAll();
	public Item findById(Long id, Integer cantidad);
	
	public Producto save (Producto producto);
	public Producto update (Producto producto, Long id);
	public void delete(Long id);
	
	

}
