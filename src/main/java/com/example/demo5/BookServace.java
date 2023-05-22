package com.example.demo5;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final DataSource dataSource;
    private final List<Book> books;

    public BookService(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        Connection connection = dataSource.getConnection();
        books = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                int author = resultSet.getInt(3);
                int pageSize = resultSet.getInt(4);
                BigDecimal publication = resultSet.getBigDecimal(5);
                int price = resultSet.getInt(6);
                books.add(new Book(name, author, pageSize, publication, price));
            }
        }
    }
    public List<Book> getBooks() {
        return books;
    }

    synchronized public Result addBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books VALUES (DEFAULT,?,?,?,?,?,?)");
                fillPreparedStatement(preparedStatement, book);
                preparedStatement.execute();

            } catch (SQLException sqlException) {
                return Result.BookIsExists;
            }
            return Result.BookCreated;
        }
        return Result.BookIsExists;
    }

    synchronized public Result deleteCar(Book book) {
        if (books.remove(book)) {
            try (Connection connection = dataSource.getConnection()) {
                String deleteCarQuery
                        = "DELETE FROM books WHERE " +
                        "name = ? " +
                        "AND author = ?" +
                        "AND pageSize = ?" +
                        "AND publication = ?" +
                        "AND price = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteBookQuery);
                fillPreparedStatement(preparedStatement, car);
                preparedStatement.execute();
            } catch (SQLException sqlException) {
                return Result.BookIsDeletedAlready;
            }
            return Result.BookDeleted;
        } else return Result.BookIsDeletedAlready;
    }

    synchronized public Result updateBook(Book oldBook, Book newBook) {
        int indexOfOldCar = books.indexOf(oldBook);
        if (indexOfOldCar !=-1) {
            try (Connection connection = dataSource.getConnection()) {
                String deleteBookQuery
                        = "UPDATE cars SET " +
                        "name = ? " +
                        "AND author = ?" +
                        "AND pageSize = ?" +
                        "AND publication = ?" +
                        "AND price = ?" +
                        "WHERE " +
                        "name = ? " +
                        "AND author = ?" +
                        "AND pageSize = ?" +
                        "AND publication = ?" +
                        "AND price = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteBookQuery);
                fillPreparedStatement(preparedStatement, newBook);
                preparedStatement.setString(6, oldBook.getName());
                preparedStatement.setInt(8, oldBook.getAuthor());
                preparedStatement.setInt(9, oldBook.getPageSize());
                preparedStatement.setBigDecimal(10, oldBook.getPublication());
                preparedStatement.setInt(11, oldBook.getPrice());
                preparedStatement.execute();
            } catch (SQLException sqlException) {
                throw new RuntimeException(sqlException);
            }
            books.set(indexOfOldCar,newBook);
            return Result.BookUpdated;
        } else return Result.BookIsDeletedAlready;
    }

    private void fillPreparedStatement(PreparedStatement preparedStatement, Book book) throws SQLException {
        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setInt(3, book.getPageSize());
        preparedStatement.setInt(4, book.getPublication());
        preparedStatement.setBigDecimal(5, book.getPrice());
    }
}