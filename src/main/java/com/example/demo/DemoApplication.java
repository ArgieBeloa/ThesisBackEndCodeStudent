package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		// ✅ No Dotenv here — Spring automatically maps environment variables
		// You can still print for debugging if needed
		System.out.println("✅ Starting Spring Boot...");
		System.out.println("Environment variables will be auto-detected by Spring.");

		SpringApplication.run(DemoApplication.class, args);
	}
}
