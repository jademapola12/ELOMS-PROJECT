package com.mini.project.controller;

import com.mini.project.dto.DepartmentDto;
import com.mini.project.entity.Department;
import com.mini.project.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // create department - POST
    @PostMapping("/set")
    public ResponseEntity<Department> setDepartment(@RequestBody DepartmentDto requestDepartment) {
        Department department = departmentService.setDepartment(requestDepartment);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    // get all departments - GET
    @GetMapping("/all")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // get department by id - GET
    @GetMapping("/id/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        if(department == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department,HttpStatus.OK);
    }

    // update department - POST

    // delete department - DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Department> deleteDepartmentById(@PathVariable Long id) {
        Department department = departmentService.deleteDepartmentById(id);
        if(department == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department,HttpStatus.OK);
    }
}
