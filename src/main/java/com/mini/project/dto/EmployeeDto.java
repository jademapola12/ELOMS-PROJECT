package com.mini.project.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private String password;

    private String contactNumber;

    private Long positionId;

    private String role;
}
