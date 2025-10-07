package com.example.demo.eventdb;

public class EventAttendance {
    private String id;
    private String studentName;
    private String timeAttended;


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeAttended() {
        return timeAttended;
    }

    public void setTimeAttended(String timeAttended) {
        this.timeAttended = timeAttended;
    }
}
