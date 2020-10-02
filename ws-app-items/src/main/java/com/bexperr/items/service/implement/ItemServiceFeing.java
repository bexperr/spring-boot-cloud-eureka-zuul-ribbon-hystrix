package com.bexperr.items.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bexperr.items.clientes.ProductoRest;
import com.bexperr.items.models.Item;
import com.bexperr.items.service.IItemService;

@Service("serviceFeign")
public class ItemServiceFeing implements IItemService{
	
	@Autowired
	private ProductoRest clienteFeign;

	@Override
	public List<Item> findAll() {
		return clienteFeign.listar().stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clienteFeign.detalle(id),cantidad);
	}

}
