package com.studentcrudoperation.service;

import com.studentcrudoperation.model.response.StudentDetailsResponse;
import com.studentcrudoperation.studentdto.AuthRequest;
import com.studentcrudoperation.studentdto.StudentDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface StudentService extends UserDetailsService{

    List<StudentDetailsResponse> getAllDetails();

    StudentDetailsResponse getDetailByEnrollment(String id);

    void deleteStudentById(Long id);

    StudentDto updateDetail(Long studentId, StudentDto studentDto);


    List<StudentDto> getUsers(int page, int limit);
}
