package com.studentcrudoperation.studentdto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long studentId;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String email;
	@NotBlank
	private String phoneNumber;
	private String enrollment;
	@NotBlank
	private String password;
	@NotBlank
	private String roles;

}
