package com.batchProject.batch.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batchProject.batch.student.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
