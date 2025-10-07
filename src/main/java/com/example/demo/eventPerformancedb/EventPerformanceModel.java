package com.example.demo.eventPerformancedb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "evaluationData")
public class EventPerformanceModel {

    @Id
    private String id;
    private double eventEvaluationRate;
    private String eventPerformanceStatus;

    private List<StudentSuggestion> studentSuggestions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getEventEvaluationRate() {
        return eventEvaluationRate;
    }

    public void setEventEvaluationRate(double eventEvaluationRate) {
        this.eventEvaluationRate = eventEvaluationRate;
    }

    public String getEventPerformanceStatus() {
        return eventPerformanceStatus;
    }

    public void setEventPerformanceStatus(String eventPerformanceStatus) {
        this.eventPerformanceStatus = eventPerformanceStatus;
    }

    public List<StudentSuggestion> getStudentSuggestions() {
        return studentSuggestions;
    }

    public void setStudentSuggestions(List<StudentSuggestion> studentSuggestions) {
        this.studentSuggestions = studentSuggestions;
    }
}
