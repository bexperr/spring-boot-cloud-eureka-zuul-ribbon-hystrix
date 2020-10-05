package com.bexperr.users.oauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.bexperr.users.oauth.clients.UsuarioFeignClient;
import com.bexperr.usuarios.commons.models.entity.Usuario;

@Service
public class UsuarioService implements UserDetailsService{
	
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

	
	@Autowired
	private UsuarioFeignClient client;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername {}",username);
		Usuario usuario = client.findByUsername(username);
		
		if(usuario == null) {
			log.error(String.format("No existe el usuario %s en el sistema", username));
			throw new UsernameNotFoundException(String.format("No existe el usuario %s en el sistema", username));
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authotiry -> log.info(String.format("Role : %s", authotiry.getAuthority())))
				.collect(Collectors.toList());
		
		log.info(String.format("Usuario autenticado %s", username));
		
		return new User(usuario.getUsername(), usuario.getPassword(),usuario.getEnabled(),
				true,true,true,authorities);
	}

}
