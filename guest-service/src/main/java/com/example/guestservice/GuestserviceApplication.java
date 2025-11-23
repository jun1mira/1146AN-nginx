package com.example.guestservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class GuestserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestserviceApplication.class, args);
	}

}
