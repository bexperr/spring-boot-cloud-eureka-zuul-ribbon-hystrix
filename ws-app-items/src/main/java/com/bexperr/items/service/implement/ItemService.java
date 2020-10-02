package com.bexperr.items.service.implement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bexperr.items.models.Item;
import com.bexperr.items.models.Producto;
import com.bexperr.items.service.IItemService;

@Service("serviceRestTemplate")
@Primary
public class ItemService implements IItemService{
	
	@Autowired
	private RestTemplate clienteRest;

	@Override
	public List<Item> findAll() {
//		List<Producto> listp = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Producto[].class));
		List<Producto> listp = Arrays.asList(clienteRest.getForObject("http://ws-app-productos/listar", Producto[].class));
		return listp.stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String,String> pathVariables = new HashMap<String,String>();
		pathVariables.put("id", id.toString());
//		Producto productovo = clienteRest.getForObject("http://localhost:8001/ver/{id}", Producto.class,pathVariables);
		Producto productovo = clienteRest.getForObject("http://ws-app-productos/ver/{id}", Producto.class,pathVariables);
		return new Item(productovo,cantidad);
	}

}
