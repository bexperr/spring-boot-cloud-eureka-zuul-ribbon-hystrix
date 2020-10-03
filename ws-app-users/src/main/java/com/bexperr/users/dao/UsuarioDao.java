package com.bexperr.users.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.bexperr.users.models.Usuario;
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
	
//	@Query(value="select u from Usuario u where u.username=?1",nativeQuery = true)
	@Query(value="select u from Usuario u where u.username=?1")
	public Usuario obtenerPorUsername(String username);
	

}
