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
@Table(name = "employee")
public class Employee {
    @Id
    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private String password;

    private String contactNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Position position;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    //ADD FIELDS FOR MORE INFO
}
