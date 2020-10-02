package com.bexperr.productos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bexperr.productos.service.IProductoService;
import com.bexperr.productos.models.Producto;

@RestController
public class ProductoController {
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer puerto;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll().stream().map(
				p -> {
//					p.setPort(Integer.parseInt(env.getProperty("local.server.port")));
					p.setPort(puerto);
					return p;
				}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id){
		Producto product = productoService.findById(id);
		product.setPort(puerto);
//		.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
//		try {
//			Thread.sleep(2000L);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return product;
	}
	
	
//	@PostMapping("/crear")	
//	public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
//		return new ResponseEntity<Producto>(productoService.save(producto),HttpStatus.CREATED);
//	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}

}
