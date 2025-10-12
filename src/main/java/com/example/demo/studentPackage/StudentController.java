package com.example.demo.studentPackage;

import com.example.demo.eventPerformancedb.StudentEventAttended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {


    @Autowired
    private StudentService studentService;

    @Autowired
    private  StudentRepository studentRepository;

//    get all student Data
    @GetMapping("/allStudentData")
    public ResponseEntity<List <StudentModel> >getAllStudentData(){
        List<StudentModel> studentModels = studentService.getAllStudentsData();

        return new ResponseEntity<>(studentModels, HttpStatus.OK);

    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getStudentByDbId(@PathVariable String id) {
        Optional<StudentModel> student = studentService.getById(id);

        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found with ID: " + id);
        }
    }



    //    get specific student by student number
    @GetMapping("/{studentNumber}")
    public StudentModel getStudentById(@PathVariable String studentNumber){

        return studentService.getByStudentNumber(studentNumber);
    }



    @PostMapping("/login")
    public String studentLogin(@RequestBody StudentModel studentModel){

        return studentService.verify(studentModel);
    }

//  add student data

    @PostMapping("/register")
    public ResponseEntity<StudentModel> registerStudent(@RequestBody StudentModel studentModel) {
        StudentModel savedStudent = studentService.createStudentData(studentModel);
        return ResponseEntity.ok(savedStudent);
    }
    @PutMapping("/update/{id}")
    public StudentModel updateStudent(@PathVariable String id, @RequestBody StudentModel updatedStudent) {
        return studentService.updateStudentData(id, updatedStudent);
    }
//update student event attended and evaluated
//    endpoint PUT http://localhost:8080/api/student/66ff98a2f1e2e1234567890a/event/EVT123/update-status?isAttended=true
@PutMapping("/{studentId}/event/{eventId}/update-status")
public StudentModel updateEventStatus(
        @PathVariable String studentId,
        @PathVariable String eventId,
        @RequestParam(required = false) Boolean isAttended,
        @RequestParam(required = false) Boolean isEvaluated
) {
    return studentService.updateEventStatus(studentId, eventId, isAttended, isEvaluated);
}
    @PutMapping("/{studentId}/event/{eventId}/update-evaluated")
    public StudentModel updateEventEvaluatedStatus(
            @PathVariable String studentId,
            @PathVariable String eventId,
            @RequestParam boolean evaluated
    ) {
        return studentService.updateEventEvaluatedStatus(studentId, eventId, evaluated);
//        http://localhost:8080/api/students/66f9b2e5a12d3b/event/6731a5b7e0a45/update-evaluated?evaluated=true
    }

//    update student event attended

//@PostMapping("/{id}/events")
//public ResponseEntity<?> addStudentEvents(@PathVariable String id,
//                                          @RequestBody List<StudentEventAttended> events) {
//    studentService.addEventToStudent(id, events);
//    return ResponseEntity.ok("Events added successfully");
//}

    @PostMapping("/{id}/evaluation")
    public ResponseEntity<?> addStudentEvaluation(@PathVariable String id,
                                              @RequestBody List<StudentRecentEvaluation> evaluations) {
        studentService.addEvaluationToStudent(id, evaluations);
        return ResponseEntity.ok("Evaluation added successfully");
    }


    @PostMapping("/{id}/upcomingEvents")
    public ResponseEntity<?> addStudentUpcomingEvents(@PathVariable String id,
                                                  @RequestBody List<StudentUpcomingEvents> events) {
        studentService.addStudentUpcomingEvents(id, events);
        return ResponseEntity.ok("Upcoming Events added successfully");
    }

    @PostMapping("/{id}/addNotification")
    public ResponseEntity<?> addStudentNotifications(@PathVariable String id,
                                                      @RequestBody List<StudentNotification> notification) {
        studentService.addStudentNotifications(id, notification);
        return ResponseEntity.ok("Notification added successfully");
    }

    @PatchMapping("/{id}/increaseNumberOfNotification")
    public ResponseEntity<StudentModel> updateNotification(
            @PathVariable String id,
            @RequestParam int numberOfNotification) {

        StudentModel updatedStudent = studentService.updateNotification(id, numberOfNotification);
        return ResponseEntity.ok(updatedStudent);
    }


    //    delete upcoming student event (specific)
    @DeleteMapping("/{studentId}/delete/upcomingEvents/{eventId}")
    public ResponseEntity<String> deleteSpecificUpcomingEvents(@PathVariable String studentId, @PathVariable String eventId){

        try {
            studentService.deleteSpecificUpcomingEvent(studentId, eventId);
            return  new ResponseEntity<>("Event remove successfully",HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("An Error occurred while removing the event", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //    delete upcoming student event (specific)
    @DeleteMapping("/{studentId}/delete/studentNotification/{eventId}")
    public ResponseEntity<String> deleteSpecificStudentNotification(@PathVariable String studentId, @PathVariable String eventId){

        try {
            studentService.deleteSpecificStudentNotification(studentId, eventId);
            return  new ResponseEntity<>("Notification remove successfully",HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("An Error occurred while removing the event", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    add student event  attended

@PostMapping("/{id}/eventAttended")
public ResponseEntity<?> addStudentEventAttended(@PathVariable String id, @RequestBody List<StudentEventAttended> newStudentEventAttended){
       studentService.addStudentEventAttended(id, newStudentEventAttended);
        return ResponseEntity.ok("Student event Attended added");
}

//add student attended and evaluated
@PostMapping("/{id}/addStudentProfileData")
public ResponseEntity<?> addStudentEventAttendedAndEvaluationDetails(@PathVariable String id, @RequestBody List<StudentEventAttendedAndEvaluationDetails> newJsonData){
    studentService.addStudentEventAttendedAndEvaluationDetails(id,newJsonData);
    return ResponseEntity.ok("Student event Attended and evaluated added");
}

//delete area

// delete event attendance
@DeleteMapping("/{studentNumber}/delete/studentAttendance/{eventId}")
public ResponseEntity<String> deleteSpecificStudentAttendance(@PathVariable String studentNumber, @PathVariable String eventId){

    try {
        studentService.deleteSpecificAttendance(studentNumber, eventId);
        return  new ResponseEntity<>("Attendance remove successfully",HttpStatus.OK);
    }catch (IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }catch (Exception e){
        return new ResponseEntity<>("An Error occurred while removing the event", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

//    delete by id
    @DeleteMapping("/{id}")
    public  void deleteById(@PathVariable String id){

        studentService.deleteStudent(id);

    }

















}
