package com.mini.project.dto;

import com.mini.project.enums.AccountStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeUpdateDto {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String address;
    private String contactNumber;
    private String emergencyContactPerson;
    private String emergencyContactNumber;
    private Long departmentId;
    private Long positionId;
    private LocalDate dateHired;
    private AccountStatus accountStatus;
    private String updatedBy;
}