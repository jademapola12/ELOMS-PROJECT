package com.mini.project.controller;

import com.mini.project.dto.EmployeeDto;
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

    @PostMapping("/save")
    public ResponseEntity<Employee> save(@RequestBody EmployeeDto dto,
                                         @RequestParam(defaultValue = "SYSTEM") String user) {
        return ResponseEntity.ok(employeeService.saveOrUpdate(dto, user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable String username) {
        return employeeService.getByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        employeeService.delete(username);
        return ResponseEntity.noContent().build();
    }
}
