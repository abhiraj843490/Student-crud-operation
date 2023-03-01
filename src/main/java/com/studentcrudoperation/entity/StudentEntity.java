package com.studentcrudoperation.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

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
	private String enrollment;
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
			flags = Pattern.Flag.CASE_INSENSITIVE)
	private String email;
	@NumberFormat
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phoneNumber;
	private String password;
	private String roles;


}
