package com.example.demo.db;


import com.example.demo.studentPackage.StudentModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class StudentPrincipal implements UserDetails {

  private final StudentModel studentModel;
    public StudentPrincipal(StudentModel studentModel) {
        this.studentModel = studentModel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton( new SimpleGrantedAuthority("USERS"));
    }

    @Override
    public String getPassword() {
        return studentModel.getStudentPassword();
    }

    @Override
    public String getUsername() {
        return studentModel.getStudentNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
