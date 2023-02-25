package com.studentcrudoperation.model.request;

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
	private String roles;
}
