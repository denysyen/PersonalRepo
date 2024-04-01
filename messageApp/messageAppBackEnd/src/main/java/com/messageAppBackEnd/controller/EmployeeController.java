package com.messageAppBackEnd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.messageAppBackEnd.exception.ResourceNotFoundException;
import com.messageAppBackEnd.model.Employee;
import com.messageAppBackEnd.repository.EmployeeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

    @PostMapping("/employees")
    public Employee postMethodName(@RequestBody Employee employee) {
         return this.employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee not exist with id =" + id));
        return ResponseEntity.ok(employee);
    }
    
    
    public Employee creatEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

}
