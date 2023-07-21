package com.example.ap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ApApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApApplication.class, args);
	}

}
