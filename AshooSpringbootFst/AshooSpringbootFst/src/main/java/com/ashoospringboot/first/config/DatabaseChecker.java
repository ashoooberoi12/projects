package com.ashoospringboot.first.config;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.sql.Connection;

@Component
public class DatabaseChecker {

    private final DataSource dataSource;

    public DatabaseChecker(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void checkConnection() {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("Successfully connected to DB: " + conn.getMetaData().getURL());
        } catch (Exception e) {
            System.err.println("Failed to connect to DB: " + e.getMessage());
        }
    }
}
