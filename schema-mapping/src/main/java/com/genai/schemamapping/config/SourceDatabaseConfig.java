package com.genai.schemamapping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
public class SourceDatabaseConfig {

    @Bean(name = "sourceDataSource")
    public DataSource sourceDataSource() {

        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:sourcedb")
                .username("sa")
                .password("")
                .driverClassName("org.h2.Driver")
                .build();
    }
}