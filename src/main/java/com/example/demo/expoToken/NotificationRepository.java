package com.example.demo.expoToken;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByScheduledTimeBeforeAndSentFalse(OffsetDateTime dateTime);

}
