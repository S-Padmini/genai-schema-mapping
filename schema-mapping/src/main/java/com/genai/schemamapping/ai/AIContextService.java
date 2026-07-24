package com.genai.schemamapping.ai;

import org.springframework.stereotype.Service;

@Service
public class AIContextService {

    private final GroqAIService groqAIService;

    public AIContextService(GroqAIService groqAIService) {
        this.groqAIService = groqAIService;
    }

    public String generateContext(String columnName,
                                  String dataType,
                                  String sampleValue) {

        String metadata = """
                Column Name: %s
                Data Type: %s
                Sample Value: %s
                """.formatted(columnName, dataType, sampleValue);

        return groqAIService.generateBusinessDescription(metadata);
    }
}