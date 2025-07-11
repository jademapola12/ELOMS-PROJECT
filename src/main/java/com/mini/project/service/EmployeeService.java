package com.mini.project.service;

import com.mini.project.dto.EmployeeDto;
import com.mini.project.dto.EmployeeUpdateDto;
//import com.mini.project.entity.Department;
import com.mini.project.entity.Department;
import com.mini.project.entity.Employee;
//import com.mini.project.entity.Position;
//import com.mini.project.repository.DepartmentRepository;
import com.mini.project.entity.Position;
import com.mini.project.repository.DepartmentRepository;
import com.mini.project.repository.EmployeeRepository;
//import com.mini.project.repository.PositionRepository;
import com.mini.project.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;
    // private final DepartmentRepository departmentRepository;
 //   private final PositionRepository positionRepository;

    public Employee save(EmployeeDto dto, String createdBy) {
        Position position = positionRepository.findById(dto.getPositionId()).orElse(null);
        if (position == null) {
            return null;
        }
        Department department = departmentRepository.findById(dto.getDepartmentId()).orElse(null);
        if (department == null) {
            return null;
        }


        Employee employee = employeeRepository.findByUsername(dto.getUsername()).orElse(null);
        if (employee == null) {
            employee = new Employee();
            employee.setUsername(dto.getUsername());
            employee.setPassword(dto.getPassword());
            employee.setCreatedAt(LocalDate.now());
            employee.setCreatedBy(createdBy);
        }
        employee.setAddress(dto.getAddress());
        employee.setEmail(dto.getEmail());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setContactNumber(String.valueOf(dto.getContactNumber()));
        employee.setEmergencyContactPerson(dto.getEmergencyContactPerson());
        employee.setEmergencyContactNumber(String.valueOf(dto.getEmergencyContactNumber()));
        employee.setUpdatedAt(LocalDate.now());
        employee.setUpdatedBy(createdBy);
        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setAccountStatus(dto.getAccountStatus());
        employee.setBirthday(dto.getBirthday());
        employee.setDateHired(dto.getDateHired());
        employee.setSex(dto.getSex());
        return employeeRepository.save(employee);
    }


    public Optional<EmployeeDto> findById(String username) {
        return employeeRepository.findById(username).map(this::mapToDto);
    }

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public void delete(String username) {
        employeeRepository.deleteById(username);
    }

//    private Department getDepartment(Long id) {
//        return id != null ? departmentRepository.findById(id).orElse(null) : null;
//    }

//    private Position getPosition(Long id) {
//        return id != null ? positionRepository.findById(id).orElse(null) : null;
 //   }

    private EmployeeDto mapToDto(Employee e) {
        return EmployeeDto.builder()
                .username(e.getUsername())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .email(e.getEmail())
                .sex(e.getSex())
                .birthday(e.getBirthday())
                .address(e.getAddress())
                .contactNumber(e.getContactNumber())
                .emergencyContactPerson(e.getEmergencyContactPerson())
                .emergencyContactNumber(e.getEmergencyContactNumber())
             //   .departmentId(e.getDepartment() != null ? e.getDepartment().getId() : null)
            //    .positionId(e.getPosition() != null ? e.getPosition().getId() : null)
                .dateHired(e.getDateHired())
                .accountStatus(e.getAccountStatus())
                .build();
    }
}