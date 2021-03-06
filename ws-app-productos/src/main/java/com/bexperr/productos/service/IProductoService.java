package com.bexperr.productos.service;

//import com.bexperr.productos.models.Producto;
import com.bexperr.commons.models.Producto;
import java.util.List;

public interface IProductoService {
	
	public List<Producto> findAll();
	public Producto findById(Long id);
	
	public Producto save(Producto producto);
	
	public void deleteById(Long id);

}
 