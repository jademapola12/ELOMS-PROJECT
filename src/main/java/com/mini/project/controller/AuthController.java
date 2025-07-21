package com.mini.project.controller;

import com.mini.project.dto.EmployeeDto;
import com.mini.project.dto.ResponseDto;
import com.mini.project.dto.auth.LoginDto;
import com.mini.project.entity.Department;
import com.mini.project.enums.MessageType;
import com.mini.project.enums.StatusType;
import com.mini.project.service.EmployeeService;
import com.mini.project.utils.DepartmentUtility;
import com.mini.project.utils.PositionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final EmployeeService employeeService;
    private final DepartmentUtility departmentUtility;
    private final PositionUtility positionUtility;


    @Autowired
    public AuthController(EmployeeService employeeService, DepartmentUtility departmentUtility, PositionUtility positionUtility) {
        this.employeeService = employeeService;
        this.departmentUtility = departmentUtility;
        this.positionUtility = positionUtility;
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody EmployeeDto employeeDto) {

        return employeeService.saveOrUpdate(employeeDto, employeeDto.getUsername()).getResponseEntity();

    }
}
