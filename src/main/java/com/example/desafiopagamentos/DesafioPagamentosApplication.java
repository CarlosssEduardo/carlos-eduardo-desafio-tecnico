package com.example.desafiopagamentos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesafioPagamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioPagamentosApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> System.out.println("-----> Sistema iniciado com sucesso! <-----");
	}

}