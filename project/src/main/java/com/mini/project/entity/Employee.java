package com.mini.project.entity;

import com.mini.project.enums.EmployeeRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    private String username;

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
