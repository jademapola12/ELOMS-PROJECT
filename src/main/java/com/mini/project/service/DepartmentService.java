package com.mini.project.service;

import com.mini.project.dto.DepartmentDto;
import com.mini.project.entity.Department;
import com.mini.project.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // create department
    public Department setDepartment(DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentDto.getId()).orElse(null);
        if (department == null) {
            department = new Department();
            department.setCreatedAt(LocalDateTime.now());
            department.setCreatedBy("mini");
        }
        department.setUpdatedAt(LocalDateTime.now());
        department.setUpdatedBy("mini");
        department.setDepartmentHead(departmentDto.getDepartmentHead());
        department.setDepartmentName(departmentDto.getDepartmentName());
        return departmentRepository.save(department);
    }

    // get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // get department by id
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    // update department

    // delete department by id
    public Department deleteDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department != null) {
            departmentRepository.delete(department);
        }
        return department;
    }

}
