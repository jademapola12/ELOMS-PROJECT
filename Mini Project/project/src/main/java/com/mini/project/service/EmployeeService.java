package com.mini.project.service;

import com.mini.project.dto.EmployeeUpdateDto;
import com.mini.project.entity.Employee;
import com.mini.project.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findById(email).orElse(null);
    }

    public Employee updateEmployee(EmployeeUpdateDto employeeUpdateDto) {
        Employee employee = employeeRepository.findById(employeeUpdateDto.getEmail()).orElse(null);
        if (employee != null) {
            employee.setPosition(employeeUpdateDto.getPosition());
            employee.setContactNumber(employeeUpdateDto.getContactNumber());
            employee.setAddress(employeeUpdateDto.getAddress());
            employeeRepository.save(employee);
        }
        return employee;
    }

    public Employee deleteEmployee(String email) {
        Employee employee = employeeRepository.findById(email).orElse(null);
        if (employee != null) {
            employeeRepository.delete(employee);
        }
        return employee;
    }


}
