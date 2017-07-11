package com.securde;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SecurdeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurdeApplication.class, args);
	}
}
