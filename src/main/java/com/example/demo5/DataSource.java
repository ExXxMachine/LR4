package com.example.demo5;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private final HikariDataSource hikariDataSource;

    public DataSource() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/demo5");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("root");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariDataSource = new HikariDataSource(hikariConfig);
        Connection connection = hikariDataSource.getConnection();
        String createTableCarsQuery
                = "CREATE TABLE IF NOT EXISTS books("
                + "book_pk serial PRIMARY KEY,"
                + "name varchar NOT NULL,"
                + "author INTEGER NOT NULL,"
                + "pageSize INT NOT NULL CHECK (pageSize BETWEEN 1 AND 700),"
                + "publication INT NOT NULL,"
                + "price INT NOT NULL,"
                + "CONSTRAINT unique_book UNIQUE (name, author, pageSize, publication, price))";
        connection.createStatement().execute(createTableBooksQuery);
    }

    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    public void close() {
        hikariDataSource.close();
    }
}