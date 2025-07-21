package com.mini.project.controller;

import com.mini.project.dto.EmployeeDto;
import com.mini.project.dto.ResponseDto;
import com.mini.project.entity.Employee;
import com.mini.project.enums.MessageType;
import com.mini.project.enums.StatusType;
import com.mini.project.service.EmployeeService;
import com.mini.project.utils.DepartmentUtility;
import com.mini.project.utils.PositionUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentUtility departmentUtility;
    private final PositionUtility positionUtility;

    @PostMapping("/save")
    public ResponseEntity<ResponseDto> save(@RequestBody EmployeeDto dto,
                                            @RequestParam(defaultValue = "SYSTEM") String user) {
        if(dto.getContactNumber().length() > 13 || dto.getContactNumber().length() <= 9){
            return ResponseDto.builder()
                    .status(StatusType.INVALID)
                    .messageType(MessageType.INVALID_CONTACT_NUMBER)
                    .build()
                    .getResponseEntity();
        }
        if(dto.getEmergencyContactNumber().length() > 13 || dto.getEmergencyContactNumber().length() <= 9) {
            return ResponseDto.builder()
                    .status(StatusType.INVALID)
                    .messageType(MessageType.INVALID_EMERGENCY_CONTACT_NUMBER)
                    .build()
                    .getResponseEntity();
        }

        if(departmentUtility.getDepartmentById(dto.getDepartmentId()) == null){
            return ResponseDto.builder()
                    .status(StatusType.ERROR)
                    .messageType(MessageType.DEPARTMENT_NOT_FOUND)
                    .build()
                    .getResponseEntity();
        }
        if(positionUtility.getPositionById(dto.getPositionId()) == null){
            return ResponseDto.builder()
                    .status(StatusType.ERROR)
                    .messageType(MessageType.DEPARTMENT_NOT_FOUND)
                    .build()
                    .getResponseEntity();
        }
        return employeeService.saveOrUpdate(dto, user).getResponseEntity();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<Employee> getById(@PathVariable String username) {
        return employeeService.getByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<List<Employee>> getBySort(@PathVariable String field) {
        return ResponseEntity.ok(employeeService.getWithSorting(field));
    }

    @GetMapping("/page/{offset}/{pageSize}")
    public ResponseEntity<Page<Employee>> getByPagination(@PathVariable int offset, @PathVariable int pageSize) {
        return ResponseEntity.ok((Page<Employee>) employeeService.getWithPagination(offset,pageSize));
    }

    @GetMapping("/sort/{field}/page/{offset}/{pageSize}")
    public ResponseEntity<Page<Employee>> getBySortAndPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        return ResponseEntity.ok((Page<Employee>) employeeService.getWithPaginationAndSorting(field,offset,pageSize));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        employeeService.delete(username);
        return ResponseEntity.noContent().build();
    }
}
