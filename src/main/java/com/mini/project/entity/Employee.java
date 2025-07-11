package com.mini.project.entity;

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

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String sex;

    private String birthday;

    private String address;

    private String contactNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    private Position position;

    @Enumerated(EnumType.STRING)
    private String status;

    private String dateHired;

    //ADD FIELDS FOR MORE INFO
}
