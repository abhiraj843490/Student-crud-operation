package com.studentcrudoperation.service.implementation;

import com.studentcrudoperation.model.response.RestResponse;
import com.studentcrudoperation.model.response.StudentDetailsResponse;
import com.studentcrudoperation.studentdto.AuthRequest;
import com.studentcrudoperation.studentdto.StudentDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.studentcrudoperation.StudentRepository;
import com.studentcrudoperation.entity.StudentEntity;
import com.studentcrudoperation.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class studentServiceImpl implements StudentService{
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<StudentDetailsResponse> getAllDetails() {
		List<StudentDetailsResponse> responseList  = new ArrayList<>();
		Iterable<StudentEntity>studentEntity = studentRepository.findAll();
		for (StudentEntity student : studentEntity) {
			StudentDetailsResponse studentDetailsResponse = new StudentDetailsResponse();
			BeanUtils.copyProperties(student, studentDetailsResponse);
			responseList.add(studentDetailsResponse);
		}
		return responseList;
	}

	@Override
	public StudentDetailsResponse getDetailByEnrollment(String id) {
		StudentDetailsResponse returnValue = new StudentDetailsResponse();
		//System.out.println(id);
		Optional<StudentEntity> studentEntity = studentRepository.findByEnrollment(id);

		BeanUtils.copyProperties(studentEntity.get(), returnValue);
		return returnValue;
	}

	@Override
	public void deleteStudentById(Long id) {
		StudentDetailsResponse response = new StudentDetailsResponse();
		studentRepository.deleteById(id);
	}

	@Override
	public StudentDto updateDetail(Long studentId, StudentDto studentDto) {
		StudentDto returnValue = new StudentDto();
		Optional<StudentEntity> student= studentRepository.findById(studentId);
//		if(student==null) {
//			throw new UsernameNotFoundException(studentId);
//		}

		student.get().setFirstName(studentDto.getFirstName());
		student.get().setLastName(student.get().getLastName());
		StudentEntity updated = studentRepository.save(student.get());
		BeanUtils.copyProperties(updated,returnValue);
		return returnValue;
	}

	@Override
	public List<StudentDto> getUsers(int page, int limit) {
		List<StudentDto> returnValue = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, limit);
		Page<StudentEntity>studentPage = studentRepository.findAll(pageable);
		List<StudentEntity> users = studentPage.getContent();
		for(StudentEntity student:users){
			StudentDto studentDto = new StudentDto();
			BeanUtils.copyProperties(student, studentDto);
			returnValue.add(studentDto);
		}
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
}
