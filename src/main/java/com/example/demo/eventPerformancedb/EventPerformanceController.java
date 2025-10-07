package com.example.demo.eventPerformancedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("api/eventPerformance")
public class EventPerformanceController {

    @Autowired
    private EventPerformanceService eventPerformanceService;

    @GetMapping("/getAll")
    public ResponseEntity<List<EventPerformanceModel>> getAllData(){

        List<EventPerformanceModel> eventPerformanceModels = eventPerformanceService.getAllEventPerformance();

        return new ResponseEntity<>(eventPerformanceModels, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<EventPerformanceModel> createEventPerformance(@RequestBody EventPerformanceModel eventPerformanceModel){

        EventPerformanceModel newEventPerformanceModel;

        newEventPerformanceModel = eventPerformanceService.createEventPerformance(eventPerformanceModel);

        return new ResponseEntity<>(newEventPerformanceModel, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/studentSuggestion")
    public ResponseEntity<?> addStudentSuggestion(@PathVariable String id,
                                                  @RequestBody List<StudentSuggestion> studentSuggestions){

        eventPerformanceService.addStudentSuggestion(id, studentSuggestions);

        return ResponseEntity.ok("Student suggestion added");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEventPerformance(@PathVariable String id){

        eventPerformanceService.deleteEventPerformance(id);
        return ResponseEntity.ok("Event Performance Delete");
    }




}
