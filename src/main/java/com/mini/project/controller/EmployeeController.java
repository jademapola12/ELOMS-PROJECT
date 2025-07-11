package com.mini.project.controller;

import com.mini.project.dto.EmployeeUpdateDto;
import com.mini.project.entity.Employee;
import com.mini.project.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create-or-update")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee requestEmployee) {
        Employee employee = employeeService.createUpdateEmployee(requestEmployee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
//    @PostMapping("/position/create-or-update")
//    public ResponseEntity<Employee> createUpdatePosition(@RequestBody Position requestEmployee) {
//        Employee employee = employeeService.createUpdateEmployee(requestEmployee);
//        return new ResponseEntity<>(employee, HttpStatus.CREATED);
//    }
////
//    @PostMapping("/department/create-or-update")
//    public ResponseEntity<Employee> createUpdateDepartment(@RequestBody Employee requestEmployee) {
//        Employee employee = employeeService.createUpdateEmployee(requestEmployee);
//        return new ResponseEntity<>(employee, HttpStatus.CREATED);
//    }
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Employee> getEmployeeByUsername(@PathVariable String username) {
        Employee employee = employeeService.getEmployeeByUsername(username);
        if (employee == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<Employee> getEmployeeByUsernameParams(@RequestParam String username) {
        Employee employee = employeeService.getEmployeeByUsername(username);
        if (employee == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeUpdateDto employeeUpdateDto) {
        Employee employee = employeeService.updateEmployee(employeeUpdateDto);
        if (employee == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Employee> deleteEmployee(@RequestParam String username) {
        Employee employee = employeeService.deleteEmployee(username);
        if (employee == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
