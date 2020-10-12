package com.guli.ekcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EkcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkcenterApplication.class, args);
	}

}
