package com.mini.project.repository;

import com.mini.project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
  Optional<Employee> findByUsername(String username);  // ✅ Correct
}

