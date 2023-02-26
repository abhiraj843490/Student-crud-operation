package com.studentcrudoperation.config;

import com.studentcrudoperation.StudentRepository;
import com.studentcrudoperation.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<StudentEntity>studentEntity = studentRepository.findByEmail(email);
        return studentEntity.map(UserInfoDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found "+email));
    }
}
