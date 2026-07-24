package com.genai.schemamapping.ingestion.jdbc;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class TargetDatabaseInitializer {

    private final TargetJdbcConnectionService jdbcConnectionService;

    public TargetDatabaseInitializer(TargetJdbcConnectionService jdbcConnectionService) {
        this.jdbcConnectionService = jdbcConnectionService;
    }

    @PostConstruct
    public void initializeTargetDatabase() {

        try (Connection connection = jdbcConnectionService.getConnection();
             Statement statement = connection.createStatement()) {

            String sql = Files.readString(
                    Paths.get("src/main/resources/sql/target-schema.sql"),
                    StandardCharsets.UTF_8
            );

            for (String query : sql.split(";")) {

                if (!query.trim().isEmpty()) {
                    statement.execute(query);
                }

            }

            System.out.println("Target database initialized successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}