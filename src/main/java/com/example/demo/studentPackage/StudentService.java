package com.example.demo.studentPackage;

import com.example.demo.eventPerformancedb.StudentEventAttended;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

//    get request
    public List<StudentModel> getAllStudentsData (){

       return studentRepository.findAll();
    }

//    get by student number
    public StudentModel getByStudentNumber(String studentNumber){

        return  studentRepository.getByStudentNumber(studentNumber);
    }
//    put request
    public StudentModel createStudentData(StudentModel studentModel){

        studentModel.setStudentPassword(encoder.encode(studentModel.getStudentPassword()));
        return  studentRepository.save(studentModel);

    }

//    add only student event attended
//    public void addStudentEventAttended(String id, StudentEventAttended studentEventAttended){
//
//        StudentModel studentModel = studentRepository.findById(id).orElse(null);
//
//
//            studentModel.getStudentEventAttendents().add(studentEventAttended);
//            studentRepository.save(studentModel);
//
//    }

// add event document
// UPDATE (Partial update, only updates non-null fields)
public StudentModel updateStudentData(String id, StudentModel updatedStudent) {
    return studentRepository.findById(id).map(student -> {
        if (updatedStudent.getStudentName() != null) {
            student.setStudentName(updatedStudent.getStudentName());
        }
        if (updatedStudent.getStudentNumber() != null) {
            student.setStudentNumber(updatedStudent.getStudentNumber());
        }
        if (updatedStudent.getCourse() != null) {
            student.setCourse(updatedStudent.getCourse());
        }
        if (updatedStudent.getDepartment() != null) {
            student.setDepartment(updatedStudent.getDepartment());
        }
        if (updatedStudent.getCategory() != null) {
            student.setCategory(updatedStudent.getCategory());
        }
        if (updatedStudent.getMacAddress() != null) {
            student.setMacAddress(updatedStudent.getMacAddress());
        }
        if (updatedStudent.getNotificationId() != null) {
            student.setNotificationId(updatedStudent.getNotificationId());
        }
        if (updatedStudent.getTokenId() != null) {
            student.setTokenId(updatedStudent.getTokenId());
        }
        if (updatedStudent.getStudentPassword() != null && !updatedStudent.getStudentPassword().isBlank()) {
            student.setStudentPassword(encoder.encode(updatedStudent.getStudentPassword()));
        }
        // numeric fields
        if (updatedStudent.getStudentAverageAttendance() > 0) {
            student.setStudentAverageAttendance(updatedStudent.getStudentAverageAttendance());
        }
        if (updatedStudent.getStudentAverageRatings() > 0) {
            student.setStudentAverageRatings(updatedStudent.getStudentAverageRatings());
        }
        // lists
        if (updatedStudent.getStudentUpcomingEvents() != null) {
            student.setStudentUpcomingEvents(updatedStudent.getStudentUpcomingEvents());
        }
        if (updatedStudent.getStudentEventAttended() != null) {
            student.setStudentEventAttended(updatedStudent.getStudentEventAttended());
        }
        if (updatedStudent.getStudentRecentEvaluations() != null) {
            student.setStudentRecentEvaluations(updatedStudent.getStudentRecentEvaluations());
        }

        return studentRepository.save(student);
    }).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
}


    public void addStudentEventAttended(String studentId, List<StudentEventAttended> newEvents) {


        Query query = new Query(Criteria.where("id").is(studentId));

        Update update = new Update()
                .push("studentEventAttended")
                .each(newEvents.toArray());

        mongoTemplate.updateFirst(query, update, StudentModel.class);
    }

    public void addEvaluationToStudent(String studentId, List<StudentRecentEvaluation> newEvents) {
        if (newEvents == null || newEvents.isEmpty()) {
            return;
        }

        Query query = new Query(Criteria.where("id").is(studentId));

        Update update = new Update()
                .push("studentRecentEvaluations")
                .each(newEvents.toArray());

        mongoTemplate.updateFirst(query, update, StudentModel.class);
    }

    public void addStudentUpcomingEvents(String studentId, List<StudentUpcomingEvents> newEvents) {
//        if (newEvents == null || newEvents.isEmpty()) {
//            return;
//        }

        Query query = new Query(Criteria.where("id").is(studentId));

        Update update = new Update()
                .push("studentUpcomingEvents")
                .each(newEvents.toArray());

        mongoTemplate.updateFirst(query, update, StudentModel.class);
    }
//add studentNotifications
public void addStudentNotifications(String studentId, List<StudentNotification> newStudentNotifications) {

    Query query = new Query(Criteria.where("id").is(studentId));

    Update update = new Update()
            .push("studentNotifications")
            .each(newStudentNotifications.toArray());

    mongoTemplate.updateFirst(query, update, StudentModel.class);
}
//add student attended and evaluation details
public void addStudentEventAttendedAndEvaluationDetails(String studentId, List<StudentEventAttendedAndEvaluationDetails> newData) {

    Query query = new Query(Criteria.where("id").is(studentId));

    Update update = new Update()
            .push("studentEventAttendedAndEvaluationDetails")
            .each(newData.toArray());

    mongoTemplate.updateFirst(query, update, StudentModel.class);
}

//PUT
        public StudentModel updateEventStatus(String studentId, String eventId, Boolean isAttended, Boolean isEvaluated) {
            Optional<StudentModel> optionalStudent = studentRepository.findById(studentId);

            if (optionalStudent.isEmpty()) {
                System.out.println("❌ Student not found");
                return null;
            }

            StudentModel student = optionalStudent.get();
            List<StudentEventAttendedAndEvaluationDetails> list = student.getStudentEventAttendedAndEvaluationDetails();

            if (list == null || list.isEmpty()) {
                System.out.println("❌ Student has no event details");
                return null;
            }

            boolean updated = false;
            for (StudentEventAttendedAndEvaluationDetails details : list) {
                if (details.getEventId().equals(eventId)) {
                    if (isAttended != null) {
                        details.setAttended(isAttended);
                    }
                    if (isEvaluated != null) {
                        details.setEvaluated(isEvaluated);
                    }
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                System.out.println("⚠️ Event not found in student's event list");
                return null;
            }

            student.setStudentEventAttendedAndEvaluationDetails(list);
            return studentRepository.save(student);
        }



    //update student number notification
public StudentModel updateNotification(String studentId, int newNotificationCount) {
    Optional<StudentModel> studentOpt = studentRepository.findById(studentId);

    if (studentOpt.isPresent()) {
        StudentModel student = studentOpt.get();
        student.setNumberOfNotification(newNotificationCount);
        return studentRepository.save(student);
    } else {
        throw new RuntimeException("Student with id " + studentId + " not found");
    }
}

    public StudentModel updateEventEvaluatedStatus(String studentId, String eventId, boolean evaluated) {
        Optional<StudentModel> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }

        StudentModel student = optionalStudent.get();

        if (student.getStudentEventAttended() != null) {
            for (StudentEventAttended attendedEvent : student.getStudentEventAttended()) {
                if (attendedEvent.getEventId().equals(eventId)) {
                    attendedEvent.setEvaluated(evaluated);
                    break;
                }
            }
        }

        return studentRepository.save(student);
    }



  //delete upcoming events specific object
    public void deleteSpecificUpcomingEvent(String studentNumber, String eventId) {

// Inject mongoTemplate and use this method:

            if (studentNumber == null || eventId == null || studentNumber.isEmpty() || eventId.isEmpty()) {
                throw new IllegalArgumentException("Student ID and Event ID cannot be null or empty");
            }

            // Match the student document
            Query query = new Query(Criteria.where("studentNumber").is(studentNumber));

            // Match subdocuments in the array that contain the eventId
            BasicDBObject eventToRemove = new BasicDBObject("eventId", eventId);

            // Remove all matching events
            Update update = new Update().pull("studentUpcomingEvents", eventToRemove);

            mongoTemplate.updateFirst(query, update, StudentModel.class);

    }

    public void deleteSpecificStudentNotification(String studentNumber, String eventId) {

        if (studentNumber == null || eventId == null || studentNumber.isEmpty() || eventId.isEmpty()) {
            throw new IllegalArgumentException("Student ID and Event ID cannot be null or empty");
        }
        Query query = new Query(Criteria.where("studentNumber").is(studentNumber));
        BasicDBObject eventToRemove = new BasicDBObject("eventId", eventId);

        // Remove all matching events
        Update update = new Update().pull("studentNotifications", eventToRemove);

        mongoTemplate.updateFirst(query, update, StudentModel.class);

    }

//    delete by student id

    public void deleteStudent(String id){

      studentRepository.deleteById(id);
    }

    public String verify(StudentModel studentModel) {

        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(studentModel.getStudentNumber(), studentModel.getStudentPassword()));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(studentModel.getStudentNumber()) ;


        return "fail";


    }


//    delete area
public void deleteSpecificAttendance(String studentNumber, String eventId) {

    if (studentNumber == null || eventId == null || studentNumber.isEmpty() || eventId.isEmpty()) {
        throw new IllegalArgumentException("Student ID and Event ID cannot be null or empty");
    }
    Query query = new Query(Criteria.where("studentNumber").is(studentNumber));

    BasicDBObject eventToRemove = new BasicDBObject("eventId", eventId);

    Update update = new Update().pull("studentEventAttended", eventToRemove);

    mongoTemplate.updateFirst(query, update, StudentModel.class);

}



//end of class
}
