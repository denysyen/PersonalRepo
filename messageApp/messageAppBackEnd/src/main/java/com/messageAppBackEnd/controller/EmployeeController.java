package com.messageAppBackEnd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messageAppBackEnd.model.Employee;
import com.messageAppBackEnd.repository.EmployeeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository  employeeRepository;
    

    public EmployeeController() {
    }


    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return this.employeeRepository.findAll();
    }

}
