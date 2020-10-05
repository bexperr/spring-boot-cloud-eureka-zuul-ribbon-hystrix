package com.bexperr.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class WsConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsConfigServerApplication.class, args);
	}

}
