package com.bexperr.items.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bexperr.items.service.implement.ItemService;
import com.bexperr.items.service.implement.ItemServiceFeing;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.bexperr.items.models.Item;
import com.bexperr.items.models.Producto;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RefreshScope
@RestController
public class ItemController {
	
	
	private static final Logger log = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceRestTemplate")
	private ItemService itemService;
	
//	@Autowired
//	@Qualifier("serviceFeign")
//	private ItemServiceFeing itemService;
	
	@Value("${configuracion.texto}")
	private String texto;
	
	@GetMapping("/listar")
	public List<Item> listar(){
		log.info("listar {}");
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		log.info("detalle {}{}",id,cantidad);
		return itemService.findById(id, cantidad);
	}
	
	public Item metodoAlternativo( Long id, Integer cantidad) {
		log.info("metodoAlternativo {}{}",id,cantidad);
        Item item = new Item();
        Producto producto = new Producto();
        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara de Error");
        producto.setPrecio(500.0);
        item.setProducto(producto);
		return item;
	}
	
	@GetMapping("/get-config")
	public ResponseEntity<?> getConfig(@Value("${server.port}") String puerto){
		log.info("getConfig {}",puerto);
		Map<String,String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", puerto);
		if(env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
		}
		return new ResponseEntity<Map<String,String>>(json,HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		log.info("crear {}",producto.toString());
		return itemService.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		log.info("editar {}{}",producto.toString(),id);
		return itemService.update(producto, id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		log.info("eliminar {}",id);
		itemService.delete(id);
	}
}
