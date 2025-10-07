package com.example.demo.expoToken;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="studentToken")
public class PushTokenModel {
    @Id
    private String id;
    private String token;
    private String userId;
    private String course;

    public PushTokenModel(){

    }

    public PushTokenModel(String id, String token, String userId) {
        this.id = id;
        this.token = token;
        this.userId = userId;
    }

    public PushTokenModel(String course, String id, String token, String userId) {
        this.course = course;
        this.id = id;
        this.token = token;
        this.userId = userId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
