package com.br.spring_security.clinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class ClinicaApplication {
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
		SpringApplication.run(ClinicaApplication.class, args);
	}

}