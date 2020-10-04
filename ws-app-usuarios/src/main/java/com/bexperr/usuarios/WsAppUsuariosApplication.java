package com.bexperr.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EntityScan({"com.bexperr.commons.models"})
@SpringBootApplication
public class WsAppUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsAppUsuariosApplication.class, args);
	}

}
