package com.bexperr.items.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bexperr.items.clientes.ProductoRest;
import com.bexperr.items.models.Item;
import com.bexperr.items.models.Producto;
import com.bexperr.items.service.IItemService;

@Service("serviceFeign")
public class ItemServiceFeing implements IItemService{
	
	
	private static final Logger log = LoggerFactory.getLogger(ItemServiceFeing.class);

	
	@Autowired
	private ProductoRest clienteFeign;

	@Override
	public List<Item> findAll() {
		log.info("findAll {}");
		return clienteFeign.listar().stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		log.info("findById {}{}",id,cantidad);
		return new Item(clienteFeign.detalle(id),cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		log.info("save {}",producto.toString());
		return clienteFeign.crear(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		log.info("update {}{}",producto.toString(),id);
		return clienteFeign.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		log.info("delete {}",id);
		clienteFeign.detalle(id);
		
	}

}
