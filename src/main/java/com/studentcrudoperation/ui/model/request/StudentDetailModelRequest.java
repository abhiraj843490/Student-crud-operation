package com.studentcrudoperation.ui.model.request;

import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetailModelRequest implements Serializable {
	private static final long serialVersionUID = 2L;
	private String firstName;
	private String lastName;
	private String email;
	private String Enrollment;
	private String password;
//	public StudentDetailModelRequest() {
//	}
//
//	public StudentDetailModelRequest(String firstName, String lastName, String email, String password) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.password=password;
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
}
