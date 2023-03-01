package com.studentcrudoperation.model.response;

import com.studentcrudoperation.studentdto.ErrorDto;
import lombok.*;
import org.springframework.http.HttpStatusCode;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Builder
//for custom exception
public class RestResponse {
    private Date timestamp;
    private Boolean isError;
    private ErrorDto errorDto;
    private  HttpStatusCode httpStatus;
    private StudentDetailsResponse data;

}
