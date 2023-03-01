package com.studentcrudoperation.exceptions;

import com.studentcrudoperation.model.response.RestResponse;
import com.studentcrudoperation.studentdto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    //specific exception handling
    @ExceptionHandler(value = {StudentServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(StudentServiceException ex, WebRequest request)
    {
        ErrorDto errorDto = new ErrorDto(ex.getMessage());

        //provide custom exception
        RestResponse errorMessage = RestResponse.builder()
                .timestamp(new Date())
                .isError(true)
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorDto(errorDto)
                                .build();

        return  ResponseEntity.badRequest().body(errorMessage);
    }

    //for all other exception like NullPointerException
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request)
    {
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        RestResponse errorMessage = RestResponse.builder()
                .timestamp(new Date())
                .isError(true)
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorDto(errorDto)
                .build();

        return  ResponseEntity.badRequest().body(errorMessage);

    }
}
