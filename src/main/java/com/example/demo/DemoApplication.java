package com.example.demo;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// Load environment variables from .env
		Dotenv dotenv = Dotenv.configure()
				.directory("./") // look in project root
				.ignoreIfMissing() // don't crash if missing
				.load();

		// Assign values only if they exist
		String mongoUri = dotenv.get("MONGODB_URI");
		String jwtSecret = dotenv.get("JWT_SECRET");
		String adminKey = dotenv.get("ADMIN_KEY");

		if (mongoUri != null) System.setProperty("MONGODB_URI", mongoUri);
		if (jwtSecret != null) System.setProperty("JWT_SECRET", jwtSecret);
		if (adminKey != null) System.setProperty("ADMIN_KEY", adminKey);

		// Optional: print for debugging
		System.out.println("✅ .env loaded successfully");
		System.out.println("Mongo URI: " + (mongoUri != null ? "FOUND" : "MISSING"));

		SpringApplication.run(DemoApplication.class, args);
	}
}
