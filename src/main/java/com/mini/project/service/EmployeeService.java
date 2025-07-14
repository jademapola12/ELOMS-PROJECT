package com.mini.project.service;

import com.mini.project.dto.EmployeeDto;
import com.mini.project.entity.Department;
import com.mini.project.entity.Employee;
import com.mini.project.entity.Position;
import com.mini.project.repository.DepartmentRepository;
import com.mini.project.repository.EmployeeRepository;
import com.mini.project.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    // to use bcrypt on password
    private BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder(12);

    public Employee saveOrUpdate(EmployeeDto dto, String currentUser) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Position position = positionRepository.findById(dto.getPositionId())
                .orElseThrow(() -> new RuntimeException("Position not found"));

        boolean isNew = !employeeRepository.existsById(dto.getUsername());

        Employee employee = employeeRepository.findById(dto.getUsername()).orElse(new Employee());
        employee.setUsername(dto.getUsername());
//        employee.setPassword(dto.getPassword());

        // if using bcrypt for password
        employee.setPassword(encoder.encode(dto.getPassword()));

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setSex(dto.getSex());
        employee.setBirthday(dto.getBirthday());
        employee.setAddress(dto.getAddress());
        employee.setContactNumber(dto.getContactNumber());
        employee.setEmergencyContactPerson(dto.getEmergencyContactPerson());
        employee.setEmergencyContactNumber(dto.getEmergencyContactNumber());
        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setDateHired(dto.getDateHired());
        employee.setAccountStatus(dto.getAccountStatus());

        if (isNew) {
            employee.setCreatedAt(LocalDate.now());
            employee.setCreatedBy(currentUser);
        }

        employee.setUpdatedAt(LocalDate.now());
        employee.setUpdatedBy(currentUser);

        return employeeRepository.save(employee);
    }

    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<EmployeeDto> getByUsername(String username) {
        return employeeRepository.findById(username).map(this::toDto);
    }

    public void delete(String username) {
        employeeRepository.deleteById(username);
    }

    private EmployeeDto toDto(Employee employee) {
        return EmployeeDto.builder()
                .username(employee.getUsername())
                .password(employee.getPassword())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .sex(employee.getSex())
                .birthday(employee.getBirthday())
                .address(employee.getAddress())
                .contactNumber(employee.getContactNumber())
                .emergencyContactPerson(employee.getEmergencyContactPerson())
                .emergencyContactNumber(employee.getEmergencyContactNumber())
                .departmentId(employee.getDepartment().getId())
                .positionId(employee.getPosition().getId())
                .dateHired(employee.getDateHired())
                .accountStatus(employee.getAccountStatus())
                .build();
    }
}
