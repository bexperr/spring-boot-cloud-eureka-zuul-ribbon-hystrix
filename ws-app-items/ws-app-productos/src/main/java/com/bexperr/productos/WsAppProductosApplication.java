package com.bexperr.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.bexperr.commons.models"})
public class WsAppProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsAppProductosApplication.class, args);
	}

}
