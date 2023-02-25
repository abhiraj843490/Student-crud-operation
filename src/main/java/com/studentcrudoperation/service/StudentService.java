package com.studentcrudoperation.service;

import com.studentcrudoperation.model.response.StudentDetailsResponse;
import com.studentcrudoperation.studentdto.AuthRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface StudentService extends UserDetailsService{

    List<StudentDetailsResponse> getAllDetails();

    StudentDetailsResponse getDetailById(Long id);

    void deleteStudentById(Long id);


    //StudentDetailsResponse updateStudentDetail(Long id);


}
