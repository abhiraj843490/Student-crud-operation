package com.studentcrudoperation.service.implementation;

import com.studentcrudoperation.model.response.StudentDetailsResponse;
import com.studentcrudoperation.studentdto.AuthRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
		for (int i=0;i<responseList.size();i++){
			System.out.println(responseList.get(i));
		}
		return responseList;
	}

	@Override
	public StudentDetailsResponse getDetailById(Long id) {
		StudentDetailsResponse response = new StudentDetailsResponse();

		Optional<StudentEntity> studentEntity = studentRepository.findById(id);
		BeanUtils.copyProperties(studentEntity, response);
		return response;
	}

	@Override
	public void deleteStudentById(Long id) {
		StudentDetailsResponse response = new StudentDetailsResponse();
		studentRepository.deleteById(id);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
}
