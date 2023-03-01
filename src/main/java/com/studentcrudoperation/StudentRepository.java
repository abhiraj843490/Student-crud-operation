package com.studentcrudoperation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.studentcrudoperation.entity.StudentEntity;

import java.util.Optional;
@EnableJpaRepositories
@Repository
public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Long> {
    Optional<StudentEntity>findByEmail(String email);


    Optional<StudentEntity> findById(Long studentId);
    Iterable<StudentEntity>findAll();


    StudentEntity save(StudentEntity student);

    void deleteById(Long id);

    boolean existsByEmail(String email);


     StudentEntity findEnrollmentByEmail(String email);

    Optional<StudentEntity> findByEnrollment(String enrollment);
}
