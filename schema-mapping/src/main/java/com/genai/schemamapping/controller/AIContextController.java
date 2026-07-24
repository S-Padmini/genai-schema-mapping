package com.genai.schemamapping.controller;

import com.genai.schemamapping.ai.AIContextService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AIContextController {

    private final AIContextService aiContextService;

    public AIContextController(AIContextService aiContextService) {
        this.aiContextService = aiContextService;
    }


    @GetMapping("/generate")
    public String generateContext(
            @RequestParam String columnName,
            @RequestParam String dataType,
            @RequestParam String sampleValue
    ) {

        return aiContextService.generateContext(
                columnName,
                dataType,
                sampleValue
        );
    }
} 