package com.studentcrudoperation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.studentcrudoperation.entity.StudentEntity;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long>{
    Optional<StudentEntity>findByEmail(String email);
}
