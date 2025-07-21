package com.mini.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.project.dto.EmployeeDto;
import com.mini.project.dto.ResponseDto;
import com.mini.project.service.EmployeeService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final EmployeeService employeeService;

    @Autowired
    public UserController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@RequestBody EmployeeDto employeeDto) {
        String updatedBy = employeeDto.getUsername();
        return employeeService.saveOrUpdate(employeeDto, updatedBy).getResponseEntity();
    }
}
