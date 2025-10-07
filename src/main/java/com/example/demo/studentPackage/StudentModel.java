package com.example.demo.studentPackage;

import com.example.demo.eventPerformancedb.StudentEventAttended;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "studentData")
public class StudentModel {

    @Id
    private String id;
    private String studentName;
    private String studentNumber;
    private String studentPassword;
    private String course;
    private String department;
    public String notificationId;
    public String macAddress;
    private double studentAverageAttendance;
    private double studentAverageRatings;
    private String tokenId;
    private String category;
    private int numberOfNotification;

    private List<StudentUpcomingEvents> studentUpcomingEvents;
    private List<StudentEventAttended> studentEventAttended;
    private List<StudentRecentEvaluation> studentRecentEvaluations;
    private List<StudentNotification> studentNotifications;
    private  List<StudentEventAttendedAndEvaluationDetails> studentEventAttendedAndEvaluationDetails;

    public List<StudentEventAttendedAndEvaluationDetails> getStudentEventAttendedAndEvaluationDetails() {
        return studentEventAttendedAndEvaluationDetails;
    }

    public void setStudentEventAttendedAndEvaluationDetails(List<StudentEventAttendedAndEvaluationDetails> studentEventAttendedAndEvaluationDetails) {
        this.studentEventAttendedAndEvaluationDetails = studentEventAttendedAndEvaluationDetails;
    }

    public int getNumberOfNotification() {
        return numberOfNotification;
    }

    public void setNumberOfNotification(int numberOfNotification) {
        this.numberOfNotification = numberOfNotification;
    }

    public List<StudentNotification> getStudentNotifications() {
        return studentNotifications;
    }

    public void setStudentNotifications(List<StudentNotification> studentNotifications) {
        this.studentNotifications = studentNotifications;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<StudentUpcomingEvents> getStudentUpcomingEvents() {
        return studentUpcomingEvents;
    }

    public void setStudentUpcomingEvents(List<StudentUpcomingEvents> studentUpcomingEvents) {
        this.studentUpcomingEvents = studentUpcomingEvents;
    }



    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public List<StudentRecentEvaluation> getStudentRecentEvaluations() {
        return studentRecentEvaluations;
    }

    public void setStudentRecentEvaluations(List<StudentRecentEvaluation> studentRecentEvaluations) {
        this.studentRecentEvaluations = studentRecentEvaluations;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public double getStudentAverageRatings() {
        return studentAverageRatings;
    }

    public void setStudentAverageRatings(double studentAverageRatings) {
        this.studentAverageRatings = studentAverageRatings;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getStudentAverageAttendance() {
        return studentAverageAttendance;
    }

    public void setStudentAverageAttendance(double studentAverageAttendance) {
        this.studentAverageAttendance = studentAverageAttendance;
    }

    public List<StudentEventAttended> getStudentEventAttended() {
        return studentEventAttended;
    }

    public void setStudentEventAttended(List<StudentEventAttended> studentEventAttendents) {
        this.studentEventAttended = studentEventAttendents;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }




}
