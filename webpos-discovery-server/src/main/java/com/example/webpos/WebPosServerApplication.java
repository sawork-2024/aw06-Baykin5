package com.example.webpos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class WebPosServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebPosServerApplication.class, args);
	}
}
