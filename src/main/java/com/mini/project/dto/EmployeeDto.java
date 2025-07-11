package com.mini.project.dto;

import com.mini.project.entity.Position;
import com.mini.project.enums.EmployeeRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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
