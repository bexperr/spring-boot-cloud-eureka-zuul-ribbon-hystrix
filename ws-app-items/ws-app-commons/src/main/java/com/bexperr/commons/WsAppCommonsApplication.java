package com.bexperr.commons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class WsAppCommonsApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(WsAppCommonsApplication.class, args);
//	}

}
