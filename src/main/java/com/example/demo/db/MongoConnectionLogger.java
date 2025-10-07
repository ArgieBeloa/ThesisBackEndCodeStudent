package com.example.demo.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MongoConnectionLogger {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database:}")
    private String database;

    @EventListener(ApplicationReadyEvent.class)
    public void logMongoConnection() {
        System.out.println("✅ Connected to MongoDB URI: " + mongoUri);
        System.out.println("✅ Using database: " + database);
    }
}

