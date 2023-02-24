package com.studentcrudoperation.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailsResponse implements Serializable {
	private static final long serialVersionUID = 3L;
	private String firstName;
	private String lastName;
	private String Enrollment;
	private String email;

}
