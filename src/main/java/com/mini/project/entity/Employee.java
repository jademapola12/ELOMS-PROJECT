package com.mini.project.entity;

import com.mini.project.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    @Enumerated(EnumType.STRING)
    private String sex;

    private LocalDate birthday;

    private String address;

    private String contactNumber;

    private String emergencyContactPerson;

    private String emergencyContactNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    private Position position;

    private LocalDate dateHired;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;


}
