package com.bexperr.users.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.bexperr.users.oauth.services.IUsuarioService;

import com.bexperr.usuarios.commons.models.entity.Usuario;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAditionalToken implements TokenEnhancer{
	
	@Autowired
	private IUsuarioService iUsuerioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//		Agregando informacion adicional al AccessToken
		Map<String, Object> info = new HashMap<String, Object>();
		Usuario usuario = iUsuerioService.findByUsername(authentication.getName());
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("correo", usuario.getEmail());		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);		
		return accessToken;
	}
	
	

}
