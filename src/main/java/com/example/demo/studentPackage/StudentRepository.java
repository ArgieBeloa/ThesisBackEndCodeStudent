package com.example.demo.studentPackage;


import org.springframework.data.mongodb.repository.MongoRepository;



public interface StudentRepository extends MongoRepository<StudentModel, String> {

    StudentModel getByStudentNumber(String studentNumber);

;

}
