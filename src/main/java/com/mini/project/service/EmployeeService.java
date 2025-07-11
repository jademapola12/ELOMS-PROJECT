package com.mini.project.service;

import com.mini.project.dto.EmployeeUpdateDto;
import com.mini.project.entity.Employee;
import com.mini.project.entity.Position;
import com.mini.project.repository.EmployeeRepository;
import com.mini.project.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
    }

    public Employee createUpdateEmployee(Employee employeeDto) {
        Position position = positionRepository.findById(employeeDto.getPositionId()).orElse(null);
        if(position == null) {
            return null;
        }

        Employee employee = employeeRepository.findByUsername(employeeDto.getUsername()).orElse(null);
        if(employee == null) {
            employee = new Employee();
            employee.setUsername(employeeDto.getUsername());
        }
        employee.setPassword(employeeDto.getPassword());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPosition(position);
        employeeRepository.save(employee);
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username).orElse(null);
    }

    public Employee updateEmployee(EmployeeUpdateDto employeeUpdateDto) {
        Employee employee = employeeRepository.findByUsername(employeeUpdateDto.getUsername()).orElse(null);
        if (employee != null) {
            employee.setPosition(employeeUpdateDto.getPosition());
            employee.setContactNumber(employeeUpdateDto.getContactNumber());
            employee.setAddress(employeeUpdateDto.getAddress());
            employeeRepository.save(employee);
        }
        return employee;
    }

    public Employee deleteEmployee(String username) {
        Employee employee = employeeRepository.findByUsername(username).orElse(null);
        if (employee != null) {
            employeeRepository.delete(employee);
        }
        return employee;
    }

}
