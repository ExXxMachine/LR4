package com.example.demo5;

public enum Result {
    BookUpdated("Книга успешно обновлена"),
    BookDeleted("Книга успешно удалена."),
    BookIsDeletedAlready("Книга уже была удалена."),
    CBookCreated("Книга успешно добавлена."),
    BookIsExists("Такая книга уже есть!");
    private final String message;

    Result(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}