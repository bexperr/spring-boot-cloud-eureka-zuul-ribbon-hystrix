package com.bexperr.usuarios.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.bexperr.usuarios.dao.models.*;

@RepositoryRestResource(path = "usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long>{
	
	@RestResource(path="buscar-username")
	public Usuario findByUsername(@Param("username") String username);
	
//	@Query(value="select u from Usuario u where u.username=?1",nativeQuery = true)
	@Query(value="select u from Usuario u where u.username=?1")
	public Usuario obtenerPorUsername(String username);
	

}