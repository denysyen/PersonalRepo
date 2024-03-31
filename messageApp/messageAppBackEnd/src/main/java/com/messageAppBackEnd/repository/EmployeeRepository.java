package com.messageAppBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messageAppBackEnd.model.Employee;
// long because my primary key (Id class type)
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> { 

}
