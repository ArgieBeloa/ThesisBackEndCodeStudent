package com.example.demo.eventdb;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "eventData")
public class  EventModel {

    @Id
    private String id;
    private String eventTitle;
    private String eventShortDescription;
    private String eventBody;
//    private int allStudentRegister;
    private int allStudentAttending;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventCategory;
    private String eventTimeLength;

    private  List<EventAgenda> eventAgendas;
    private List<EventAttendance> eventAttendances;
    private EventStats eventStats;
    private EventOrganizer eventOrganizer;


    private List<EvaluationQuestion> evaluationQuestions;
    private List<EventEvaluationDetails> eventEvaluationDetails;
    private List<EventPerformanceDetails> eventPerformanceDetails;
    private List<EventStudentEvaluation> eventStudentEvaluations;

    private double eventAveragePerformance;

/* Example Data

{
    "eventTitle": "Enrollment Hub",
   "eventShortDescription": "Enrollment hub is a new method of enrollment",
    "eventBody": "New Beginning has started where, enrollment new way in one place. All in one place flow and more.",
    "eventDate": "July 07 2025",
    "eventTime": "8:00 Am",
    "eventLocation": "DABBA",
    "eventCategory": "Academic",
    "eventTimeLength": "July 07 - 25 2025",
    "allStudentAttending": 0,
    "eventAgendas":[
        {
        "agendaTime": "8:00Am",
        "agendaTitle": "Sleep form",
        "agendaHost": "Dean"
        },
        {
        "agendaTime": "9:00Am",
        "agendaTitle": "Curriculom Update",
        "agendaHost": "Registrar"
        }
    ],
    "eventStats": {
        "attending": 0,
        "interested": 0
    },
    "eventOrganizer":{
        "organizerName": "SSPC",
        "organizerEmail":"SSPC.@gmail.com"

    },
    "eventEvaluationDetails": [],
    "eventPerformanceDetails": [],
    "eventAveragePerformance": 0.0
}



 */

//    getters and setters


    public List<EvaluationQuestion> getEvaluationQuestions() {
        return evaluationQuestions;
    }

    public void setEvaluationQuestions(List<EvaluationQuestion> evaluationQuestions) {
        this.evaluationQuestions = evaluationQuestions;
    }

    public List<EventStudentEvaluation> getEventStudentEvaluations() {
        return eventStudentEvaluations;
    }

    public void setEventStudentEvaluations(List<EventStudentEvaluation> eventStudentEvaluations) {
        this.eventStudentEvaluations = eventStudentEvaluations;
    }

    public List<EventAttendance> getEventAttendances() {
        return eventAttendances;
    }

    public void setEventAttendances(List<EventAttendance> eventAttendances) {
        this.eventAttendances = eventAttendances;
    }

    public int getAllStudentAttending() {
        return allStudentAttending;
    }

    public void setAllStudentAttending(int allStudentAttending) {
        this.allStudentAttending = allStudentAttending;
    }

    public EventOrganizer getEventOrganizer() {
        return eventOrganizer;
    }

    public void setEventOrganizer(EventOrganizer eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public List<EventAgenda> getEventAgendas() {
        return eventAgendas;
    }

    public void setEventAgendas(List<EventAgenda> eventAgendas) {
        this.eventAgendas = eventAgendas;
    }

    public EventStats getEventStats() {
        return eventStats;
    }

    public void setEventStats(EventStats eventStats) {
        this.eventStats = eventStats;
    }

    public String getEventTimeLength() {
        return eventTimeLength;
    }

    public void setEventTimeLength(String eventTimeLength) {
        this.eventTimeLength = eventTimeLength;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    /*


    {
         "eventTitle": "test 2",
      "eventShortDescription": "event short description",
       "eventBody": "This is body",
         "eventAveragePerformance": 0,
         "eventDate": "2025-07-07",
        "eventLocation": "DABBA",
        "eventCategory":"Technology"
    }
     */
    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventShortDescription() {
        return eventShortDescription;
    }

    public void setEventShortDescription(String eventShortDescription) {
        this.eventShortDescription = eventShortDescription;
    }

    public String getEventBody() {
        return eventBody;
    }

    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }

//    public int getAllStudentRegister() {
//        return allStudentRegister;
//    }
//
//    public void setAllStudentRegister(int allStudentRegister) {
//        this.allStudentRegister = allStudentRegister;
//    }
//
//    public int getAllStudentInteresting() {
//        return allStudentInteresting;
//    }

//    public void setAllStudentInteresting(int allStudentInteresting) {
//        this.allStudentInteresting = allStudentInteresting;
//    }

    public double getEventAveragePerformance() {
        return eventAveragePerformance;
    }

    public void setEventAveragePerformance(double eventAveragePerformance) {
        this.eventAveragePerformance = eventAveragePerformance;
    }

    public List<EventEvaluationDetails> getEventEvaluationDetails() {
        return eventEvaluationDetails;
    }

    public void setEventEvaluationDetails(List<EventEvaluationDetails> eventEvaluationDetails) {
        this.eventEvaluationDetails = eventEvaluationDetails;
    }

    public List<EventPerformanceDetails> getEventPerformanceDetails() {
        return eventPerformanceDetails;
    }

    public void setEventPerformanceDetails(List<EventPerformanceDetails> eventPerformanceDetails) {
        this.eventPerformanceDetails = eventPerformanceDetails;
    }
}
