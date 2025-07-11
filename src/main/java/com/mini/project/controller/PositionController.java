package com.mini.project.controller;

import com.mini.project.dto.PositionDto;
import com.mini.project.entity.Position;
import com.mini.project.service.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    private final PositionService positionService;
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    // create & update
    @PostMapping("/set")
    public ResponseEntity<Position> setPosition(@RequestBody PositionDto requestPosition) {
        Position position = this.positionService.setPosition(requestPosition);
        return new ResponseEntity<>(position, HttpStatus.CREATED);
    }

    // get all
    @GetMapping("/all")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    // get by id
    @GetMapping("/id/{id}")
    public ResponseEntity<Position> getPositionById(@PathVariable Long id) {
        Position position = this.positionService.getPositionById(id);
        if (position == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    // delete by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Position> deletePositionById(@PathVariable Long id) {
        Position position = this.positionService.deletePositionById(id);
        if (position == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(position, HttpStatus.OK);
    }
}
