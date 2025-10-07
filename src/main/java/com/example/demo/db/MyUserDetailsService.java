package com.example.demo.db;

import com.example.demo.studentPackage.StudentModel;
import com.example.demo.studentPackage.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
 private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        StudentModel studentModel = studentRepository.getByStudentNumber(username);

        if (studentModel == null){
            throw new UsernameNotFoundException("user not found");
        }
        return new StudentPrincipal(studentModel);
    }
}
