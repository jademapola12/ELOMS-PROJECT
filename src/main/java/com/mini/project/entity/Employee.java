package com.mini.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name ="first_name")
    private String firstName;
    @Column(name ="last_name")
    private String lastName;
    private String email;
    private String sex;
    private LocalDate birthday;
    private String address;
    @Column(name ="contact_number")
    private String contactNumber;
    @Column(name ="emergency_contact_person")
    private String emergencyContactPerson;
    @Column(name ="emergency_contact_number")
    private String emergencyContactNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;


    @ManyToOne(fetch = FetchType.LAZY)
    private Position position;
    @Column(name ="date_hired")
    private LocalDate dateHired;

    @Enumerated(EnumType.STRING)
    @Column(name ="account_status")
    private AccountStatus accountStatus;
    @Column(name ="created_at")
    private LocalDate createdAt;
    @Column(name ="created_by")
    private String createdBy;
    @Column(name ="updated_at")
    private LocalDate updatedAt;
    @Column(name ="updated_by")
    private String updatedBy;
}
