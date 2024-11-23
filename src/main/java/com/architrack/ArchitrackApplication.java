package com.architrack;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;

@SpringBootApplication
public class ArchitrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchitrackApplication.class, args);
		
		
		Map<String, PasswordEncoder>encoders = new HashMap<>();
		Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("", 8, 185000, SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		encoders.put("pbkdf2", passwordEncoder);
		DelegatingPasswordEncoder delegating = new DelegatingPasswordEncoder("pbkdf2", encoders);
		delegating.setDefaultPasswordEncoderForMatches(passwordEncoder);
		
		
		String token = delegating.encode("admin123");
		System.out.println("My Hash 1"+token);
		

		String tokenOne = delegating.encode("admin123");
		System.out.println("My Hash 2"+tokenOne);
	}

}
