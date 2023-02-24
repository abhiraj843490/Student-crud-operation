package com.studentcrudoperation.Student.dto;

import java.io.Serializable;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long studentId;
	private String firstName;
	private String lastName;
	private String email;
	private String Enrollment;
	private String password;
	private String encryptedPassword;

//	public StudentDto() {
//	}
//
//	public StudentDto(Long studentId, String firstName, String lastName, String email, String password, String encryptedPassword) {
//		this.studentId = studentId;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.password = password;
//		this.encryptedPassword = encryptedPassword;
//	}
//
//	public Long getStudentId() {
//		return studentId;
//	}
//
//	public void setStudentId(Long studentId) {
//		this.studentId = studentId;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getEncryptedPassword() {
//		return encryptedPassword;
//	}
//
//	public void setEncryptedPassword(String encryptedPassword) {
//		this.encryptedPassword = encryptedPassword;
//	}
}
