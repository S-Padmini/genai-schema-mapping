package com.genai.schemamapping.document;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentExtractionService documentExtractionService;

    public DocumentController(DocumentExtractionService documentExtractionService) {
        this.documentExtractionService = documentExtractionService;
    }

    @PostMapping("/extract")
    public String extractDocument(@RequestParam("file") MultipartFile file) {

        try {

            File tempFile = File.createTempFile(
                    "uploaded-",
                    file.getOriginalFilename()
            );

            file.transferTo(tempFile);

            String extractedText =
                    documentExtractionService.extractText(tempFile);

            tempFile.delete();

            return extractedText;

        } catch (Exception e) {

            return "Document extraction failed: "
                    + e.getMessage();
        }
    }
}