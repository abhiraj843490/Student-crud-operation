package com.studentcrudoperation.controller;

import com.studentcrudoperation.exceptions.StudentServiceException;
import com.studentcrudoperation.model.response.RestResponse;
import com.studentcrudoperation.model.response.ErrorMessages;
import com.studentcrudoperation.shared.Utils;
import com.studentcrudoperation.studentdto.AuthRequest;
import com.studentcrudoperation.studentdto.ErrorDto;
import com.studentcrudoperation.studentdto.StudentDto;
import com.studentcrudoperation.StudentRepository;
import com.studentcrudoperation.entity.StudentEntity;
import com.studentcrudoperation.service.JwtService;
import com.studentcrudoperation.service.StudentService;
import com.studentcrudoperation.model.request.StudentDetailModelRequest;
import com.studentcrudoperation.model.response.StudentDetailsResponse;

import jakarta.validation.constraints.Null;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping()
@RestController
public class StudentController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	StudentService studentService;

	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	JwtService jwtService;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	Utils utils;

	@PostMapping(value = "/student")
	public ResponseEntity<?> postStudentDetail(@RequestBody StudentDetailModelRequest studentDetailModelRequest) throws Exception
	{
		if(studentDetailModelRequest.getFirstName().isEmpty())
		{
			throw new StudentServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		StudentDto studentDto = new StudentDto();
		StudentEntity student = new StudentEntity();

		BeanUtils.copyProperties(studentDetailModelRequest,studentDto);

		studentDto.setPassword(encoder.encode(studentDto.getPassword()));
		String enrollment = utils.generateUserId(10);

		studentDto.setEnrollment(enrollment);
		BeanUtils.copyProperties(studentDto, student);
		StudentEntity saved = studentRepository.save(student);
		StudentDetailsResponse returnValue = new StudentDetailsResponse();

		if(!studentRepository.existsByEmail(student.getEmail())){
			BeanUtils.copyProperties(saved, returnValue);
			RestResponse response  = RestResponse.builder()
					.timestamp(new Date())
					.isError(false)
					.httpStatus(HttpStatusCode.valueOf(HttpStatus.CREATED.value()))
					.data(returnValue)
					.build();

			return ResponseEntity.ok(response);
		}
		else
		{
			ErrorDto errorDto = new ErrorDto();
			RestResponse response  = RestResponse.builder()
					.timestamp(new Date())
					.isError(true)
					.httpStatus(HttpStatusCode.valueOf(HttpStatus.ALREADY_REPORTED.value()))
					.errorDto(errorDto)
					.data(null)
					.build();

			return ResponseEntity.ok(response);
		}

	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<StudentDetailsResponse>getAllDetails(){
		return studentService.getAllDetails();
	}

	@GetMapping("/student/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public StudentDetailsResponse getDetailById(@PathVariable (value = "id") String id) {
		StudentDetailsResponse returnValue ;
		returnValue = studentService.getDetailByEnrollment(id);
		return returnValue;
	}
	@GetMapping("/paging")
	public List<StudentDetailsResponse>getUsers
			(@RequestParam(value = "page", defaultValue = "0")Integer page,
			 @RequestParam(value = "limit", defaultValue = "4")Integer limit){
		List<StudentDetailsResponse> returnValue = new ArrayList<>();
		List<StudentDto> users = studentService.getUsers(page, limit);
		for(StudentDto studentDto:users){
			StudentDetailsResponse student = new StudentDetailsResponse();
			BeanUtils.copyProperties(studentDto, student);
			returnValue.add(student);
		}
		return returnValue;
	}
	@PutMapping("/student/{id}")
	public StudentDetailsResponse updateDetail(@PathVariable Long studentId, @RequestBody StudentDetailModelRequest
														   studentDetailModelRequest){
		StudentDetailsResponse returnValue = new StudentDetailsResponse();
		StudentDto studentDto = new StudentDto();
		BeanUtils.copyProperties(studentDetailModelRequest, studentDto);

		StudentDto updateDetail = studentService.updateDetail(studentId, studentDto);
		BeanUtils.copyProperties(updateDetail, returnValue);
		return returnValue;
	}

	@DeleteMapping("/student/{id}")
	public void deleteStudentById(@PathVariable(value = "id")Long id){
		studentService.deleteStudentById(id);
	}
//	String enroll = s
	@PostMapping("/sign")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
		Authentication authentication
				= authenticationManager
				.authenticate(new
						UsernamePasswordAuthenticationToken(
								authRequest.getEmail(),
						authRequest.getPassword()));

		if(authentication.isAuthenticated()){
			return jwtService.generateToken(authRequest.getEmail());
		}
		else {
			throw new UsernameNotFoundException("invalid user request");
		}
		//this allows to all to get the token,
		// but this is not a correct way
		// so, need to authenticate the user
		//return jwtService.generateToken(authRequest.getEmail());

	}
}
