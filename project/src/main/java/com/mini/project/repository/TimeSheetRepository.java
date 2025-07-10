package com.mini.project.repository;

import com.mini.project.entity.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeSheetRepository extends JpaRepository<TimeSheet,Long> {
    Long id(Long id);
}
