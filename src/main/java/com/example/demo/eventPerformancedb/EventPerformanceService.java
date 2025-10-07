package com.example.demo.eventPerformancedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventPerformanceService {

    @Autowired
    private EventPerformanceRepository eventPerformanceRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    public List<EventPerformanceModel> getAllEventPerformance(){

        return eventPerformanceRepository.findAll();
    }

    public EventPerformanceModel createEventPerformance(EventPerformanceModel eventPerformanceModel){

        return eventPerformanceRepository.save(eventPerformanceModel);
    }

    public void addStudentSuggestion(String id, List<StudentSuggestion> newStudentSuggestions){

        Query query = new Query(Criteria.where("id").is(id));

        Update update = new Update()
                .push("studentSuggestions")
                .each(newStudentSuggestions.toArray());

        mongoTemplate.updateFirst(query, update, EventPerformanceModel.class);

    }





    public void deleteEventPerformance(String id){

        eventPerformanceRepository.deleteById(id);
    }
}
