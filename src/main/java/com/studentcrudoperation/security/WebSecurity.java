package com.studentcrudoperation.security;

import com.studentcrudoperation.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurity{

    private final StudentService studentService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(StudentService studentService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.studentService = studentService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder=
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(studentService).passwordEncoder(bCryptPasswordEncoder);

        http.csrf().disable().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/student")
                .permitAll().and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,"/allDetails")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,"/student/{id}")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.DELETE, "/student/{id}")
                .permitAll()
                .anyRequest().authenticated();
        return http.build();
    }

}
