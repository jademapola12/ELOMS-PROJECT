package com.mini.project.service;

import com.mini.project.dto.PositionDto;
import com.mini.project.entity.Position;
import com.mini.project.repository.PositionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    // create and update
    public Position setPosition(PositionDto positionDto) {
        Position position = positionRepository.findById(positionDto.getId()).orElse(null);
        if (position == null) {
            position = new Position();
            position.setCreatedAt(LocalDateTime.now());
            position.setCreatedBy("mini");
        }
        position.setUpdatedAt(LocalDateTime.now());
        position.setUpdatedBy("mini");
        position.setPositionName(positionDto.getPositionName());
        return positionRepository.save(position);
    }

    // get all
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    // get by id
    public Position getPositionById(Long id) {
        return positionRepository.findById(id).orElse(null);
    }

    // delete
    public Position deletePositionById(Long id) {
        Position position = positionRepository.findById(id).orElse(null);
        if (position != null) {
            positionRepository.delete(position);
        }
        return position;
    }

}
