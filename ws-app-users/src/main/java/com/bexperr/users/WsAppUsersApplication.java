package com.bexperr.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class WsAppUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsAppUsersApplication.class, args);
	}

}
