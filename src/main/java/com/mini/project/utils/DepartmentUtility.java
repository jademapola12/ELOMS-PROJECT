package com.mini.project.utils;

import com.mini.project.entity.Department;
import com.mini.project.repository.DepartmentRepository;
import org.springframework.stereotype.Component;

@Component
public class DepartmentUtility {

    private final DepartmentRepository departmentRepository;

    public DepartmentUtility(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department getDepartmentById(long id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
