package com.example.demo.eventPerformancedb;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventPerformanceRepository extends MongoRepository<EventPerformanceModel, String> {
}
