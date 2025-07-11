package com.mini.project.controller;

import com.mini.project.dto.EmployeeDto;
import com.mini.project.dto.EmployeeUpdateDto;
import com.mini.project.entity.Employee;
import com.mini.project.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/set/{createdBy}")
    public ResponseEntity<Employee> create(@RequestBody EmployeeDto dto,
                                           @PathVariable String createdBy) {
        return ResponseEntity.ok(employeeService.save(dto, createdBy));
    }

    @GetMapping("/{username}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable String username) {
        return employeeService.findById(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        employeeService.delete(username);
        return ResponseEntity.noContent().build();
    }
}
