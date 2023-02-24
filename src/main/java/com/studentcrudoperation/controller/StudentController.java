package com.studentcrudoperation.controller;

import com.studentcrudoperation.Student.dto.StudentDto;
import com.studentcrudoperation.StudentRepository;
import com.studentcrudoperation.entity.StudentEntity;
import com.studentcrudoperation.service.StudentService;
import com.studentcrudoperation.ui.model.request.StudentDetailModelRequest;
import com.studentcrudoperation.ui.model.response.StudentDetailsResponse;

import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;

	
	@PostMapping("/student")
	public StudentDetailsResponse postStudentDetail(@RequestBody StudentDetailModelRequest
																studentDetailModelRequest) {
		System.out.println(studentDetailModelRequest.getFirstName());

		StudentDto studentDto = new StudentDto();

		BeanUtils.copyProperties(studentDetailModelRequest,studentDto);
		System.out.println("BeanUtils "+studentDto.getFirstName().toString());

		StudentDetailsResponse returnValue = new StudentDetailsResponse();
		StudentDto createdStudent = studentService.fetchAllDetail(studentDto);

		BeanUtils.copyProperties(createdStudent, returnValue);
		System.out.println("studentDto "+returnValue.getFirstName());
		return returnValue;
	}

	@GetMapping("/allDetails")
	public List<StudentDetailsResponse>getAllDetails(){
		return studentService.getAllDetails();
	}

	@GetMapping("/student/{id}")
	public StudentDetailsResponse fetchById(@PathVariable (value = "id") Long id) {
		StudentDetailsResponse response = new StudentDetailsResponse();
		response = studentService.fetchById(id);
		return response;
	}
	@DeleteMapping("/student/{id}")
	public void deleteStudentById(@PathVariable(value = "id")Long id){
//		StudentDetailsResponse response = new StudentDetailsResponse();
		studentService.deleteStudentById(id);
		//return response;
	}
//	@PutMapping("/{id}")
//	public StudentDetailsResponse updateStudentDetail(@PathVariable(value = "id")Long id) {
//		StudentDetailsResponse response = new StudentDetailsResponse();
//		response=studentService.updateStudentDetail(id);
//		return response;
//	}

}
