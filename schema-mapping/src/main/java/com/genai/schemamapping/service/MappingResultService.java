package com.genai.schemamapping.service;

import com.genai.schemamapping.entity.MappingResult;
import com.genai.schemamapping.repository.MappingResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappingResultService {

    private final MappingResultRepository mappingResultRepository;

    public MappingResultService(MappingResultRepository mappingResultRepository) {
        this.mappingResultRepository = mappingResultRepository;
    }

    public MappingResult saveMappingResult(MappingResult mappingResult) {
        return mappingResultRepository.save(mappingResult);
    }

    public List<MappingResult> getAllMappingResults() {
        return mappingResultRepository.findAll();
    }
}