package com.bexperr.users.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.bexperr.usuarios.commons.models.entity"})
public class WsAppOauthApplication implements CommandLineRunner{
	
	@Autowired
	BCryptPasswordEncoder passwordEncode;

	public static void main(String[] args) {
		SpringApplication.run(WsAppOauthApplication.class, args);
	}

	
	/*
	 * Generar passwords
	 */
	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		
		for(int i=0; i< 4 ; i++) {
			String passBCrypt = passwordEncode.encode(password);
			System.out.println(passBCrypt);
		}
		
	}

}
