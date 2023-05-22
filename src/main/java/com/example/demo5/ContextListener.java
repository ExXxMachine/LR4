package com.example.demo5;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.SQLException;

public class ContextListener implements ServletContextListener {

    private final BookService bookService;
    private final DataSource dataSource;

    public ContextListener() throws SQLException {
        dataSource = new DataSource();
        bookService = new BookService(dataSource);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("bookService", bookService);
        servletContextEvent.getServletContext().setAttribute("dataSource", dataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        dataSource.close();
    }
}