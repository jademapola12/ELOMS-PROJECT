package com.mini.project.entity;

import com.mini.project.enums.EmployeeRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private String password;

    private String contactNumber;

    private String position;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

}
