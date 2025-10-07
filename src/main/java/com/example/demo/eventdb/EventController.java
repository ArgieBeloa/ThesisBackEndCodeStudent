package com.example.demo.eventdb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@CrossOrigin
public class EventController {

    @Autowired
    private EventService eventService;


//  get all
    @GetMapping("/getAll")
    public ResponseEntity<List<EventModel>> getAllEvents(){

          List<EventModel> events = eventService.getAllEvents();

          return new ResponseEntity<>(events,HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<List<EventModel>> getEventById(@PathVariable String id){

      List<EventModel> event = eventService.getEventById(id);

      return  new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<EventModel>> getEventByCategory(@PathVariable String category){

          List<EventModel> event = eventService.getEventByCategory(category);

        return new ResponseEntity<>(event,  HttpStatus.OK);
    }
    @GetMapping("/{eventTitle}")
    public ResponseEntity<List<EventModel>> getEventByEventTitle(@PathVariable String eventTitle){

         List<EventModel> event = eventService.getEventByEventTitle(eventTitle);

         return new ResponseEntity<>(event, HttpStatus.OK);
    }

//    add data
    @PostMapping("/add")
    public ResponseEntity<EventModel> createEvent(@RequestBody EventModel newEventModel){

        EventModel newEvent;
        newEvent = eventService.createEvent(newEventModel);

        return new ResponseEntity<> (newEvent, HttpStatus.CREATED);
    }


//    add event details
@PostMapping("/{id}/eventEvaluationDetails")
public ResponseEntity<?> addEvents(@PathVariable String id,
                                          @RequestBody List<EventEvaluationDetails> newEvaluationDetails) {
    eventService.addEventEvaluationDetails(id,newEvaluationDetails);
    return ResponseEntity.ok("Event Evaluation details added successfully");
}
//add evaluation question
    @PostMapping("/{id}/addEvaluationQuestion")
    public ResponseEntity<?> addEvaluationQuestion(@PathVariable String id,
                                       @RequestBody List<EvaluationQuestion> newEvaluationQuestion) {
        eventService.addEvaluationQuestion(id,newEvaluationQuestion);
        return ResponseEntity.ok("Evaluation question added successfully");
    }

//add event attendance
@PostMapping("/{id}/addEventAttendance")
public ResponseEntity<?> addEventAttendance(@PathVariable String id,
                                   @RequestBody List<EventAttendance> newEventAttendance) {
    eventService.addEventAttendance(id,newEventAttendance);
    return ResponseEntity.ok("Student attendance added successfully");
}

    //add event student evaluation
    @PostMapping("/{id}/addStudentEvaluation")
    public ResponseEntity<?> addStudentEvaluation(@PathVariable String id,
                                                @RequestBody List<EventStudentEvaluation> evaluation) {
        eventService.addEventStudentEvaluation(id,evaluation);
        return ResponseEntity.ok("Student evaluation added successfully");
    }

//    add evaluation details

    //    add event details
    @PostMapping("/{id}/eventPerformanceDetails")
    public ResponseEntity<?> addEventPerformance(@PathVariable String id,
                                       @RequestBody List<EventPerformanceDetails> newPerformanceDetails) {
        eventService.addPerformanceDetails(id,newPerformanceDetails);
        return ResponseEntity.ok("Event Performance details added successfully");
    }

//    add overall Performance
@PostMapping("/{id}/overallPerformance")
public ResponseEntity<?> addEventPerformanceDetails(@PathVariable String id,
                                             @RequestBody List<EventPerformanceDetails> newData) {
    eventService.addPerformanceDetails(id,newData);
    return ResponseEntity.ok("Event Overall Performance details added successfully");
}


//    update all student attending in event document
   @PutMapping("/update/{id}")
public ResponseEntity<EventModel>  updateEventAttending (@PathVariable String id, @RequestBody EventModel newEvent){

    Optional<EventModel> updatedEvent = eventService.updateEvent(id, newEvent);

        return updatedEvent.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
   }
//   update all student attending
    @PutMapping("/{id}/attending")
    public String updateAttending(
            @PathVariable String id,
            @RequestParam int count
    ) {
        eventService.updateAllStudentAttending(id, count);
        return "allStudentAttending updated successfully!";
    }


//    delete by id
@DeleteMapping("/delete/{id}")
public  void deleteById(@PathVariable String id){

    eventService.deleteEvent(id);

}

//end of class
}
