package com.homework.comyno.nlf.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.homework.comyno.nlf.entities.Book;

public class BookBaseInfo {

  private final String isbn;
  private final String title;

  // Adding this to make integration testing deserialization easier
  @JsonCreator
  public BookBaseInfo(@JsonProperty("isbn") String isbn, @JsonProperty("title") String title) {
    this.isbn = isbn;
    this.title = title;
  }

  public BookBaseInfo(Book book) {
    this.isbn = book.getIsbn();
    this.title = book.getTitle();
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }
}
