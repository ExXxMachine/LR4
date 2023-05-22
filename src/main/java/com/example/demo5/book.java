package com.example.demo5;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Book implements Serializable {
    private final String name;
    private final int author;
    private final int pageSize;
    private final BigDecimal publication;
    private final int price;

    @JsonCreator
    public Book(
            @JsonProperty("name") String name,
            @JsonProperty("author") int author,
            @JsonProperty("pageSize") int pageSize,
            @JsonProperty("publication") BigDecimal publication,
            @JsonProperty("price") int price) {
        this.name = name;
        this.author = author;
        this.pageSize = pageSize;
        this.publication = publication;
        this.price = price;
    }

    public String getName() {
        return name;
    }



    public int getAuthor() {
        return author;
    }

    public int getPageSize() {
        return pageSize;
    }

    public BigDecimal getPublication() {
        return publication;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return author == book.author && pageSize == book.pageSize && price == book.price && name.equals(book.name) && publication.equals(book.publication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, pageSize, publication, price);
    }
}