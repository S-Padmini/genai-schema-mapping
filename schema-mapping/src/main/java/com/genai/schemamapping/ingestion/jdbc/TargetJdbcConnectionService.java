package com.genai.schemamapping.ingestion.jdbc;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

@Service
public class TargetJdbcConnectionService {

    private static final String URL = "jdbc:h2:file:./data/targetdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public Connection getConnection() throws Exception {

        Class.forName("org.h2.Driver");

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}