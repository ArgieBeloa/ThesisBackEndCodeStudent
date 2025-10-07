package com.example.demo.eventdb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;



public interface EventRepository extends MongoRepository<EventModel, String> {

    List<EventModel> getByEventCategory(String category);
   List< EventModel>getEventByEventTitle(String eventTitle);
   List<EventModel> getEventById(String id);

}
