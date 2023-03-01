package com.studentcrudoperation.studentdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.Assert.notEmpty;
import static org.springframework.util.Assert.notNull;
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorDto {
    private String message;
//    private List<String> fields;

}
