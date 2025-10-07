package com.example.demo.expoToken;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PushTokenRepo extends MongoRepository<PushTokenModel, String> {
    PushTokenModel findByid(String id);
    PushTokenModel findByUserId(String userId);
    List<PushTokenModel> findByCourse(String course);
}
