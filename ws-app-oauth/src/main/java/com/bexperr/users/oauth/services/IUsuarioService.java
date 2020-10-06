package com.bexperr.users.oauth.services;
import com.bexperr.usuarios.commons.models.entity.Usuario;
public interface IUsuarioService {
	
	public Usuario findByUsername(String username);

}
