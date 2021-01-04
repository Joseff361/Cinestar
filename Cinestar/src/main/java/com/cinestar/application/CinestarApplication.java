package com.cinestar.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.logging.Level;
import java.util.logging.Logger; 

@SpringBootApplication
public class CinestarApplication implements CommandLineRunner {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	Logger logger = Logger.getLogger(CinestarApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(CinestarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String passwordBcrypt = passwordEncoder.encode("12345");
		logger.log(Level.INFO, passwordBcrypt);
	}

}