package com.studentcrudoperation.service;

import com.studentcrudoperation.Student.dto.StudentDto;
import com.studentcrudoperation.ui.model.response.StudentDetailsResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface StudentService extends UserDetailsService{
    StudentDto fetchAllDetail(StudentDto studentDto);

    List<StudentDetailsResponse> getAllDetails();

    StudentDetailsResponse fetchById(Long id);

    void deleteStudentById(Long id);

    //StudentDetailsResponse updateStudentDetail(Long id);


}
