package com.example.demo5;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "BooksServlet", value = "/BooksServlet")
public class BooksServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private BookService bookService;

    @Override
    public void init() {
        bookService = (BookService) getServletContext().getAttribute("bookService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(bookService.getBooks()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Book book = objectMapper.readValue(request.getInputStream(), Book.class);
        response.getWriter().write(bookService.addBook(book).getMessage());
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Book> books = objectMapper.readValue(request.getInputStream(), new TypeReference<List<Book>>() {});
        response.getWriter().write(bookService.updateCar(books.get(0),books.get(1)).getMessage());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Book book = objectMapper.readValue(request.getInputStream(), Book.class);
        response.getWriter().write(bookService.deleteBook(book).getMessage());
    }
}
