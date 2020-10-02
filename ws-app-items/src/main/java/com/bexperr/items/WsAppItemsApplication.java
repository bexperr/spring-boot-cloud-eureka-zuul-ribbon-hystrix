package com.bexperr.items;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@RibbonClient(name="ws-app-productos")
@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class WsAppItemsApplication {
	
	
	private static final Logger log = LoggerFactory.getLogger(WsAppItemsApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(WsAppItemsApplication.class, args);
	}

}
