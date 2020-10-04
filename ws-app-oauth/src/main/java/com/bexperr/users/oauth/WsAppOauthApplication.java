package com.bexperr.users.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.bexperr.usuarios.commons.models.entity"})
public class WsAppOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsAppOauthApplication.class, args);
	}

}
