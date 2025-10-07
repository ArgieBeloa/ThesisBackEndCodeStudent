package com.example.demo.studentPackage;

public class StudentUpcomingEvents {

    private  String eventId;
    private  String eventTitle;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private int numberOfStudentAttending;
    private String studentWant;


    public String getStudentWant() {
        return studentWant;
    }

    public void setStudentWant(String studentWant) {
        this.studentWant = studentWant;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public int getNumberOfStudentAttending() {
        return numberOfStudentAttending;
    }

    public void setNumberOfStudentAttending(int numberOfStudentAttending) {
        this.numberOfStudentAttending = numberOfStudentAttending;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
