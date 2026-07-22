package com.genai.schemamapping.service;

import com.genai.schemamapping.entity.SourceTable;
import com.genai.schemamapping.repository.SourceTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceTableService {

    private final SourceTableRepository sourceTableRepository;

    public SourceTableService(SourceTableRepository sourceTableRepository) {
        this.sourceTableRepository = sourceTableRepository;
    }

    public SourceTable saveSourceTable(SourceTable sourceTable) {
        return sourceTableRepository.save(sourceTable);
    }

    public List<SourceTable> getAllSourceTables() {
        return sourceTableRepository.findAll();
    }
}