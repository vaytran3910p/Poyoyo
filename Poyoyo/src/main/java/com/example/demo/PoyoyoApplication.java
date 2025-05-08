package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.demo.repository")
public class PoyoyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoyoyoApplication.class, args);
	}

}
