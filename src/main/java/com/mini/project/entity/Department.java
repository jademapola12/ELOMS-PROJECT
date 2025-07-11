package com.mini.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="department_name")
    private String departmentName;
    @Column(name ="department_head")
    private String departmentHead;
    @Column(name="created_at")
    private LocalDate createdAt;
    @Column(name="updated_at")
    private LocalDate updatedAt;
    @Column(name="updated_by")
    private String updatedby;
    @Column(name="created_by")
    private String createdby;



}
