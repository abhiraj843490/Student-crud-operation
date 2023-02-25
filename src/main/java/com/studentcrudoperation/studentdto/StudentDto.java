package com.studentcrudoperation.studentdto;

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
	private String roles;

}
