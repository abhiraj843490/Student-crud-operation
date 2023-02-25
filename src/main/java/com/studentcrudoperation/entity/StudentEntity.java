package com.studentcrudoperation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="student")
public class StudentEntity implements Serializable {
	private static final long serialVersionUID = 5L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long studentId;
	private String firstName;
	private String lastName;
	private String Enrollment;
	private String email;
	private String password;
	private String roles;


}
