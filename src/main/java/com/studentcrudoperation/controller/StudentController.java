package com.studentcrudoperation.controller;

import com.studentcrudoperation.studentdto.AuthRequest;
import com.studentcrudoperation.studentdto.StudentDto;
import com.studentcrudoperation.StudentRepository;
import com.studentcrudoperation.entity.StudentEntity;
import com.studentcrudoperation.service.JwtService;
import com.studentcrudoperation.service.StudentService;
import com.studentcrudoperation.model.request.StudentDetailModelRequest;
import com.studentcrudoperation.model.response.StudentDetailsResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/student")
	public StudentDetailsResponse postStudentDetail(@RequestBody StudentDetailModelRequest
																studentDetailModelRequest) {
		StudentDto studentDto = new StudentDto();

		BeanUtils.copyProperties(studentDetailModelRequest,studentDto);
		studentDto.setPassword(encoder.encode(studentDto.getPassword()));

		StudentEntity student = new StudentEntity();
		BeanUtils.copyProperties(studentDto, student);

		StudentEntity saved = studentRepository.save(student);
		StudentDetailsResponse returnValue = new StudentDetailsResponse();
		BeanUtils.copyProperties(saved, returnValue);
		return returnValue;
	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<StudentDetailsResponse>getAllDetails(){
		return studentService.getAllDetails();
	}

	@GetMapping("/student/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public StudentDetailsResponse getDetailById(@PathVariable (value = "id") Long id) {
		StudentDetailsResponse response = new StudentDetailsResponse();
		response = studentService.getDetailById(id);
		return response;
	}

	@DeleteMapping("/student/{id}")
	public void deleteStudentById(@PathVariable(value = "id")Long id){
		studentService.deleteStudentById(id);
	}

	@PostMapping("/authenticate")
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
