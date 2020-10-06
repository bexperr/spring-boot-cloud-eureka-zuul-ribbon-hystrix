package com.bexperr.zuul.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/productos/listar","/api/items/listar","/api/usuarios/usuarios").permitAll()
		.antMatchers(HttpMethod.GET,"/api/productos/ver/{id}","/api/productos/ver/{id}/cantidad/{cantidad}")
		.hasAnyRole("ADMIN","USER")
		.antMatchers(HttpMethod.POST,"/api/productos/crear","/api/items/crear").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/productos/editar/{id}","/api/items/editar/{id}","/api/usuarios/usuarios/{id}").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/productos/eliminar/{id}","/api/items/eliminar/{id}","/api/usuarios/usuarios/{id}").hasRole("ADMIN")
		.anyRequest().authenticated();
	}
	
	/**
	 * Genera el token con una palabra clave que se encuentra dentro del servidor de configuracion
	 * @return
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("algun_codigo_secreto_aeiou");
		return tokenConverter;
	}
	
	/**
	 * Almacena el token converter
	 * @return
	 */
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	

}
