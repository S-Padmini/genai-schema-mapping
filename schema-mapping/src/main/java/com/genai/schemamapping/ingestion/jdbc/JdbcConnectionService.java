package com.genai.schemamapping.ingestion.jdbc;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class JdbcConnectionService {

    public Connection getConnection(String url, String username, String password) throws SQLException {

        return DriverManager.getConnection(url, username, password);

    }
}