package com.bexperr.productos.dao;

import org.springframework.data.repository.CrudRepository;
//import com.bexperr.productos.models.Producto;
import com.bexperr.commons.models.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long>{

}
