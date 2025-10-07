package com.example.demo.eventdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<EventModel> getAllEvents(){

         return eventRepository.findAll();
     }

     public EventModel createEvent(EventModel eventModel){
         return eventRepository.save(eventModel);

     }

     public List<EventModel> getEventById(String id){

        return eventRepository.getEventById(id);
     }


     public void addEventEvaluationDetails(String id, List<EventEvaluationDetails> newEventEvaluationDetails){


//             if (newEventEvaluationDetails == null ||newEventEvaluationDetails.isEmpty()) {
//                 return;
//             }

             Query query = new Query(Criteria.where("id").is(id));

             Update update = new Update()
                     .push("EventEvaluationDetails")
                     .each(newEventEvaluationDetails.toArray());

             mongoTemplate.updateFirst(query, update, EventModel.class);
    }

    public void addPerformanceDetails(String id, List<EventPerformanceDetails> newEventPerformanceDetails){


//        if (newEventPerformanceDetails == null ||newEventPerformanceDetails.isEmpty()) {
//            return;
//        }

        Query query = new Query(Criteria.where("id").is(id));

        Update update = new Update()
                .push("eventPerformanceDetails")
                .each(newEventPerformanceDetails.toArray());

        mongoTemplate.updateFirst(query, update, EventModel.class);
    }
    //    get by category
    public List<EventModel> getEventByCategory(String category){

        return eventRepository.getByEventCategory(category);
    }

    public List<EventModel> getEventByEventTitle(String eventTitle){

        return eventRepository.getEventByEventTitle(eventTitle);
    }
    public void deleteEvent(String id){

        eventRepository.deleteById(id);
    }
    //     update event all student Attending

    public Optional<EventModel> updateEvent(String id, EventModel eventModel){

        return  eventRepository.findById(id).map(event->{
            event.setId(eventModel.getId());
            event.setEventTitle(eventModel.getEventTitle());
            event.setEventShortDescription(eventModel.getEventShortDescription());
            event.setEventBody(eventModel.getEventBody());
            event.setEventDate(eventModel.getEventDate());
            event.setEventTime(eventModel.getEventTime());
            event.setEventLocation(eventModel.getEventLocation());
            event.setEventCategory(eventModel.getEventCategory());
            event.setEventTimeLength(eventModel.getEventTimeLength());
            event.setEventAgendas(eventModel.getEventAgendas());
            event.setEventStats(eventModel.getEventStats());
            event.setEventOrganizer(eventModel.getEventOrganizer());
            event.setEventEvaluationDetails(eventModel.getEventEvaluationDetails());
            event.setEventPerformanceDetails(eventModel.getEventPerformanceDetails());
            event.setAllStudentAttending(eventModel.getAllStudentAttending());


            return eventRepository.save(event);
        });


    }

//    event attended
public void addEventAttendance(String id, List<EventAttendance> newEventAttendance){

    Query query = new Query(Criteria.where("id").is(id));

    Update update = new Update()
            .push("eventAttendances")
            .each(newEventAttendance.toArray());

    mongoTemplate.updateFirst(query, update, EventModel.class);
}

public void addEventStudentEvaluation(String id, List<EventStudentEvaluation> newEventStudentEvaluated){

        Query query = new Query(Criteria.where("id").is(id));

        Update update = new Update()
                .push("eventStudentEvaluations")
                .each(newEventStudentEvaluated.toArray());

        mongoTemplate.updateFirst(query, update, EventModel.class);
    }

//    add evaluation question
public void addEvaluationQuestion(String id, List<EvaluationQuestion> evaluationQuestions){

    Query query = new Query(Criteria.where("id").is(id));

    Update update = new Update()
            .push("evaluationQuestions")
            .each(evaluationQuestions.toArray());

    mongoTemplate.updateFirst(query, update, EventModel.class);
}

//add overall performance
    public  void addEventPerformanceDetails(String id, List<EventPerformanceDetails> newEventPerformanceDetails){
        Query query = new Query(Criteria.where("id").is(id));

        Update update = new Update()
                .push("eventPerformanceDetails")
                .each(newEventPerformanceDetails.toArray());

        mongoTemplate.updateFirst(query, update, EventModel.class);
    }

//    update event area
public void updateAllStudentAttending(String eventId, int newCount) {
    EventModel event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));
    event.setAllStudentAttending(newCount);
    eventRepository.save(event);
}

//class end bracket
}
