package com.mini.project.utils;

import com.mini.project.entity.Department;
import com.mini.project.entity.Position;
import com.mini.project.repository.DepartmentRepository;
import com.mini.project.repository.PositionRepository;
import org.springframework.stereotype.Component;

@Component
public class PositionUtility {

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    public PositionUtility(DepartmentRepository departmentRepository,
                           PositionRepository positionRepository) {
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
    }

    public Position getPositionById(long id) {
        return positionRepository.findById(id).orElse(null);
    }
}
