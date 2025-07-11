//package com.mini.project.controller;
//
//import com.mini.project.dto.TimeSheetDto;
//import com.mini.project.entity.TimeSheet;
//import com.mini.project.repository.TimeSheetRepository;
//import com.mini.project.service.TimeSheetService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/employee/timesheet")
//public class TimeSheetController {
//
//    private final TimeSheetService timeSheetService;
//    private final TimeSheetRepository timeSheetRepository;
//
//    public TimeSheetController(TimeSheetService timeSheetService, TimeSheetRepository timeSheetRepository) {
//        this.timeSheetService = timeSheetService;
//        this.timeSheetRepository = timeSheetRepository;
//    }
//
//    // POST - creating new timesheet (need email, date, time in, time out)
//    @PostMapping("/create")
//    public ResponseEntity<TimeSheet> createTimeSheet (
//            @RequestBody TimeSheet requestTimeSheet
//    )
//    {
//        TimeSheet timeSheet = timeSheetService.createTimeSheet(requestTimeSheet);
//        return new ResponseEntity<>(timeSheet, HttpStatus.CREATED);
//    }
//
//    // GET TIME SHEET
//    @GetMapping("/all")
//    public List<TimeSheet> getAllTimeSheets() {
//        return timeSheetService.getAllTimeSheets();
//    }
//
//    // GET TIME SHEET BY ID - using params
//    @GetMapping("/id")
//    public ResponseEntity<TimeSheet> getTimesheetById(@RequestParam Long id) {
//        TimeSheet timeSheet = timeSheetService.getTimeSheet(id);
//        if (timeSheet == null) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(timeSheet, HttpStatus.OK);
//    }
//
//    // GET TIME SHEET BY ID - /id/{input id}
//    @GetMapping("/id/{id}")
//    public ResponseEntity<TimeSheet> getTimeSheetById(@PathVariable Long id) {
//        TimeSheet timeSheet = timeSheetService.getTimeSheet(id);
//        if (timeSheet == null) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(timeSheet, HttpStatus.OK);
//    }
//
//    // UPDATE TIME SHEET
//    @PostMapping("/update")
//    public ResponseEntity<TimeSheet> updateTimeSheet(@RequestBody TimeSheetDto timeSheetDto) {
//        TimeSheet timeSheet = timeSheetService.updateTimeSheet(timeSheetDto);
//        if (timeSheet == null) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(timeSheet, HttpStatus.OK);
//    }
//
//    // DELETE TIME SHEET - by id using params
//    @DeleteMapping("/delete")
//    public ResponseEntity<TimeSheet> deleteTimeSheetById(@RequestParam Long id) {
//        TimeSheet timeSheet = timeSheetService.getTimeSheet(id);
//        if (timeSheet == null) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(timeSheet, HttpStatus.OK);
//    }
//
//
//}
