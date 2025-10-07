package com.example.demo.expoToken;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;

    private String course;

    private String title;

    private String body;


    private OffsetDateTime scheduledTime;


    private boolean sent = false;

    // Constructors, Getters, and Setters

    public Notification() {}


    public String getId() {
        return id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public OffsetDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(OffsetDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public void setId(String id) {
        this.id = id;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
