package com.genai.schemamapping.document;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DocumentExtractionService {

    private final Tika tika = new Tika();

    public String extractText(File file) {

        try {
            return tika.parseToString(file);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to extract document content",
                    e
            );
        }
    }
}