package com.genai.schemamapping.ingestion.jdbc;

import com.genai.schemamapping.ingestion.MetadataExtractionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class SourceDatabaseInitializer implements CommandLineRunner {

    private final DataSource sourceDataSource;
    private final MetadataExtractionService metadataExtractionService;

    public SourceDatabaseInitializer(
            @Qualifier("sourceDataSource") DataSource sourceDataSource,
            MetadataExtractionService metadataExtractionService) {

        this.sourceDataSource = sourceDataSource;
        this.metadataExtractionService = metadataExtractionService;
    }

    @Override
    public void run(String... args) throws Exception {

        try (Connection connection = sourceDataSource.getConnection()) {

            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("sql/source-schema.sql")
            );

            System.out.println("Source database initialized successfully!");

            // Extract metadata
            metadataExtractionService.extractTables();

        }
    }
}