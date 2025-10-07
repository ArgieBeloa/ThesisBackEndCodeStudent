package com.example.demo.studentPackage;

public class StudentEventAttendedAndEvaluationDetails {
    private String eventId;
    private String eventTitle;
    private String eventDateAndTime;
    private Boolean isAttended;
    private Boolean isEvaluated;

    public String getEventDateAndTime() {
        return eventDateAndTime;
    }

    public void setEventDateAndTime(String eventDateAndTime) {
        this.eventDateAndTime = eventDateAndTime;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Boolean getAttended() {
        return isAttended;
    }

    public void setAttended(Boolean attended) {
        isAttended = attended;
    }

    public Boolean getEvaluated() {
        return isEvaluated;
    }

    public void setEvaluated(Boolean evaluated) {
        isEvaluated = evaluated;
    }
}
