package com.bexperr.items.service.implement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import com.bexperr.items.models.Item;
import com.bexperr.commons.models.Producto;
import com.bexperr.items.service.IItemService;

@Service("serviceRestTemplate")
@Primary
public class ItemService implements IItemService{
	
	
	private static final Logger log = LoggerFactory.getLogger(ItemService.class);

	
	@Autowired
	private RestTemplate clienteRest;

	@Override
	public List<Item> findAll() {
		log.info("findAll {}");
//		List<Producto> listp = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Producto[].class));
		List<Producto> listp = Arrays.asList(clienteRest.getForObject("http://ws-app-productos/listar", Producto[].class));
		return listp.stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		log.info("findById {}",id,cantidad);
		Map<String,String> pathVariables = new HashMap<String,String>();
		pathVariables.put("id", id.toString());
//		Producto productovo = clienteRest.getForObject("http://localhost:8001/ver/{id}", Producto.class,pathVariables);
		Producto productovo = clienteRest.getForObject("http://ws-app-productos/ver/{id}", Producto.class,pathVariables);
		return new Item(productovo,cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		log.info("save {}",producto.toString());
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response = clienteRest.exchange("http://ws-app-productos/crear", HttpMethod.POST,body,Producto.class);
		Producto responseP = response.getBody();
		return responseP;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		log.info("update {}",id);
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		Map<String,String> pathVariables = new HashMap<String,String>();
		pathVariables.put("id", id.toString());
		ResponseEntity<Producto> response = clienteRest.exchange("http://ws-app-productos/editar/{id}", HttpMethod.PUT,body,Producto.class,pathVariables);
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		log.info("delete {}",id);
		Map<String,String> pathVariables = new HashMap<String,String>();
		pathVariables.put("id", id.toString());
		clienteRest.delete("http://ws-app-productos/eliminar/{id}",pathVariables);
		
	}

}
