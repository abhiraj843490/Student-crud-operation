package com.studentcrudoperation.service.imple;

import com.studentcrudoperation.ui.model.response.StudentDetailsResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.studentcrudoperation.StudentRepository;
import com.studentcrudoperation.Student.dto.StudentDto;
import com.studentcrudoperation.entity.StudentEntity;
import com.studentcrudoperation.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public StudentDto fetchAllDetail(StudentDto student) {
		System.out.println("student "+student.getFirstName());
		StudentEntity studentEntity = new StudentEntity();
		BeanUtils.copyProperties(student, studentEntity);

		studentEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(student.getPassword()));
		StudentEntity storedDetails = studentRepository.save(studentEntity);

		StudentDto returnValue = new StudentDto();
		BeanUtils.copyProperties(storedDetails, returnValue);
		return returnValue;
		
	}

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
	public StudentDetailsResponse fetchById(Long id) {
		StudentDetailsResponse response = new StudentDetailsResponse();

		Optional<StudentEntity> studentEntity = studentRepository.findById(id);
		BeanUtils.copyProperties(studentEntity, response);
		return response;
	}

	@Override
	public void deleteStudentById(Long id) {
		StudentDetailsResponse response = new StudentDetailsResponse();
		studentRepository.deleteById(id);
		//return  null;

	}

//	@Override
//	public StudentDetailsResponse updateStudentDetail(Long id) {
//		Optional<StudentEntity> response = studentRepository.findById(id);//
//
//		return null;
//	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return null;
	}
}
