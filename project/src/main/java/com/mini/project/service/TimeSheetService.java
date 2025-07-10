package com.mini.project.service;

import com.mini.project.dto.TimeSheetDto;
import com.mini.project.entity.TimeSheet;
import com.mini.project.repository.TimeSheetRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TimeSheetService {

    private final TimeSheetRepository timeSheetRepository;
    public TimeSheetService(EmployeeService employeeService, TimeSheetRepository timeSheetRepository) {
        this.timeSheetRepository = timeSheetRepository;
    }

    // create time sheet - input date, time in, time out
    public TimeSheet createTimeSheet(TimeSheet timeSheet) {
        return timeSheetRepository.save(timeSheet);
    }

    // get time sheet - all employees
    public List<TimeSheet> getAllTimeSheets() {
        return timeSheetRepository.findAll();
    }

    // get time sheet - by id
    public TimeSheet getTimeSheet(Long id) {
        return timeSheetRepository.findById(id).orElse(null);
    }

    // update time sheet
    public TimeSheet updateTimeSheet(TimeSheetDto timeSheetDto) {
        TimeSheet timeSheet = timeSheetRepository.findById(timeSheetDto.getId()).orElse(null);
        if (timeSheet != null) {
            timeSheet.setDate(timeSheetDto.getDate());
            timeSheet.setStartTime(timeSheetDto.getStartTime());
            timeSheet.setEndTime(timeSheetDto.getEndTime());
            timeSheetRepository.save(timeSheet);
        }
        return timeSheet;
    }

    // delete time sheet - by id
    public TimeSheet deleteTimeSheet(Long id) {
        TimeSheet timeSheet = timeSheetRepository.findById(id).orElse(null);
        if (timeSheet != null) {
            timeSheetRepository.delete(timeSheet);
        }
        return timeSheet;
    }

}
