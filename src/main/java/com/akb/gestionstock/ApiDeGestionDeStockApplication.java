package com.akb.gestionstock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiDeGestionDeStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDeGestionDeStockApplication.class, args);
	}

}
