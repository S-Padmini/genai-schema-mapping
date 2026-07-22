package com.genai.schemamapping.service;

import com.genai.schemamapping.entity.TargetColumn;
import com.genai.schemamapping.repository.TargetColumnRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetColumnService {

    private final TargetColumnRepository targetColumnRepository;

    public TargetColumnService(TargetColumnRepository targetColumnRepository) {
        this.targetColumnRepository = targetColumnRepository;
    }

    public TargetColumn saveTargetColumn(TargetColumn targetColumn) {
        return targetColumnRepository.save(targetColumn);
    }

    public List<TargetColumn> getAllTargetColumns() {
        return targetColumnRepository.findAll();
    }
}