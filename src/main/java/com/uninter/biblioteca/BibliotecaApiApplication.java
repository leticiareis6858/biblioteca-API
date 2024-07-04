package com.uninter.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// classe principal da aplicação
@SpringBootApplication
// anotação que define os pacotes da aplicação que serão escaneados
@ComponentScan(basePackages = {
		"com.uninter.biblioteca.controller",
		"com.uninter.biblioteca.controller.dto",
		"com.uninter.biblioteca.model",
		"com.uninter.biblioteca.model.entity",
		"com.uninter.biblioteca.model.enumeration",
		"com.uninter.biblioteca.repository",
		"com.uninter.biblioteca.service",
		"com.uninter.biblioteca.swagger"})
public class BibliotecaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApiApplication.class, args);
	}

}
