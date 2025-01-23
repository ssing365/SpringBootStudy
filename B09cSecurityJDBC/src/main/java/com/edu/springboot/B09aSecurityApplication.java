package com.edu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@SpringBootApplication
public class B09aSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(B09aSecurityApplication.class, args);
		
		String passwd = 
				PasswordEncoderFactories.createDelegatingPasswordEncoder()
					.encode("1234");
		System.out.println("Encoded Password : "+passwd);
		/*
		 * {bcrypt}$2a$10$fIkGVXvmBHc2xaqz20EYbuJBEirtR3cg4.i77qOYo/IFHzFrNPpQS
		 * {bcrypt}$2a$10$AnYA9zc3QBSEp7u156MHfuzObspC/EdlX/3A.JnnLS9vfr8kGnYV6
		 * {bcrypt}$2a$10$2upYAXH9vPThph4nE08OruqboKlNDcI7CvjkpaHBze1tumI8Clf5q
		 * {bcrypt}$2a$10$dktMUIPRbO5KVdTS/6Pok.KRCIJ3aFP.q9DJ3X3wxDmOuQWEjfyma
		 */
	}
}
