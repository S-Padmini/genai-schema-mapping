package com.genai.schemamapping.controller;

import com.genai.schemamapping.entity.SourceTable;
import com.genai.schemamapping.repository.SourceTableRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metadata")
public class MetadataController {


    private final SourceTableRepository sourceTableRepository;


    public MetadataController(
            SourceTableRepository sourceTableRepository) {

        this.sourceTableRepository = sourceTableRepository;
    }



    @GetMapping("/tables")
    public List<SourceTable> getTables() {

        return sourceTableRepository.findAll();

    }

}
