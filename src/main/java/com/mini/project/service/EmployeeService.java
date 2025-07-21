package com.mini.project.service;

import com.mini.project.dto.EmployeeDto;
import com.mini.project.dto.ResponseDto;
import com.mini.project.entity.Department;
import com.mini.project.entity.Employee;
import com.mini.project.entity.Position;
import com.mini.project.enums.MessageType;
import com.mini.project.enums.StatusType;
import com.mini.project.repository.DepartmentRepository;
import com.mini.project.repository.EmployeeRepository;
import com.mini.project.repository.PositionRepository;
import com.mini.project.utils.DepartmentUtility;
import com.mini.project.utils.PositionUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    // to use bcrypt on password
    private final BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder(12);
    private final DepartmentUtility departmentUtility;
    private final PositionUtility positionUtility;

    public ResponseEntity<ResponseDto> createUser(EmployeeDto employeeDto, String username){
        // validation, forked from `EmployeeController`
        if(employeeDto.getContactNumber().length() > 13 || employeeDto.getContactNumber().length() <= 9){
            return ResponseDto.builder()
                    .status(StatusType.INVALID)
                    .messageType(MessageType.INVALID_CONTACT_NUMBER)
                    .build()
                    .getResponseEntity();
        }
        if(employeeDto.getEmergencyContactNumber().length() > 13 || employeeDto.getEmergencyContactNumber().length() <= 9) {
            return ResponseDto.builder()
                    .status(StatusType.INVALID)
                    .messageType(MessageType.INVALID_EMERGENCY_CONTACT_NUMBER)
                    .build()
                    .getResponseEntity();
        }

        if(departmentUtility.getDepartmentById(employeeDto.getDepartmentId()) == null){
            return ResponseDto.builder()
                    .status(StatusType.ERROR)
                    .messageType(MessageType.DEPARTMENT_NOT_FOUND)
                    .build()
                    .getResponseEntity();
        }
        if(positionUtility.getPositionById(employeeDto.getPositionId()) == null){
            return ResponseDto.builder()
                    .status(StatusType.ERROR)
                    .messageType(MessageType.DEPARTMENT_NOT_FOUND)
                    .build()
                    .getResponseEntity();
        }
        return this.saveOrUpdate(employeeDto, username).getResponseEntity();
    }

    public ResponseDto saveOrUpdate(EmployeeDto dto, String currentUser) {

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Position position = positionRepository.findById(dto.getPositionId())
                .orElseThrow(() -> new RuntimeException("Position not found"));

        boolean isNew = !employeeRepository.existsById(dto.getUsername());

        Employee employee = employeeRepository.findById(dto.getUsername()).orElse(new Employee());
        employee.setUsername(dto.getUsername());

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

        employee = employeeRepository.save(employee);
        return ResponseDto.builder()
                .data(employee)
                .status(StatusType.SUCCESS)
                .messageType(MessageType.SUCCESSFULLY_SAVED)
                .build();
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getByUsername(String username) {
        return employeeRepository.findById(username);
    }

    public void delete(String username) {
        employeeRepository.deleteById(username);
    }

    // for authentication of username & password login
    AuthenticationManager authenticationManager;

    JwtService jwtService;

    public String verify(Employee employee) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(employee.getUsername(), employee.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(employee.getUsername());
        }

        return "Login failed";
    }

    // find employees with sorting (ASC = ascending)
    public List<Employee> getWithSorting(String field){
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    // find employees with pagination
    public Page<Employee> getWithPagination(int offset, int pageSize) {
        return employeeRepository.findAll(PageRequest.of(offset, pageSize));
    }

    // find employees with pagination and sorting
    public Page<Employee> getWithPaginationAndSorting(String field, int offset, int pageSize) {
        return employeeRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(Sort.Direction.ASC, field)));
    }

}
