package com.bexperr.items.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import com.bexperr.items.models.*;
//@FeignClient(name="ws-app-productos", url="localhost:8001")
@FeignClient(name="ws-app-productos")
public interface ProductoRest {
	
	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);
	

}
