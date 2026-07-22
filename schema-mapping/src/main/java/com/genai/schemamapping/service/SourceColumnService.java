package com.genai.schemamapping.service;

import com.genai.schemamapping.entity.SourceColumn;
import com.genai.schemamapping.repository.SourceColumnRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceColumnService {

    private final SourceColumnRepository sourceColumnRepository;

    public SourceColumnService(SourceColumnRepository sourceColumnRepository) {
        this.sourceColumnRepository = sourceColumnRepository;
    }

    public SourceColumn saveSourceColumn(SourceColumn sourceColumn) {
        return sourceColumnRepository.save(sourceColumn);
    }

    public List<SourceColumn> getAllSourceColumns() {
        return sourceColumnRepository.findAll();
    }
}