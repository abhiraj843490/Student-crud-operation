package com.studentcrudoperation.filter;

import com.studentcrudoperation.StudentRepository;
import com.studentcrudoperation.config.UserInfoDetails;
import com.studentcrudoperation.config.UserInfoUserDetailsService;
import com.studentcrudoperation.entity.StudentEntity;
import com.studentcrudoperation.model.response.RestResponse;
import com.studentcrudoperation.service.JwtService;
import com.studentcrudoperation.service.StudentService;
import com.studentcrudoperation.studentdto.ErrorDto;
import com.studentcrudoperation.studentdto.StudentDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private UserInfoUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //1. Header
        String authHeader = request.getHeader("Authorization");
        //2. username & token from authHeader
        String token =null;
        String username=null;
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        token = authHeader.substring(7);
        username = jwtService.extractUsername(token);

        String enroll = username.substring(0,10);
        String email = username.substring(10,username.length());
        //3. validate
        try {
            if (email != null && SecurityContextHolder.getContext().getAuthentication() != null &&
                    enroll.equals(request.getHeader("enrollment"))) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails
                                    , null
                                    , userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            else{
                if (email != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

                    if (jwtService.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails
                                        , null
                                        , userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
        }catch (Exception e)
        {
            ErrorDto errorDto = new ErrorDto(e.getMessage());
            RestResponse.builder()
                    .timestamp(new Date())
                    .isError(true)
                    .httpStatus(HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value()))
                    .errorDto(errorDto)
                    .data(null)
                    .build();
        }
    filterChain.doFilter(request,response);
    }
}
