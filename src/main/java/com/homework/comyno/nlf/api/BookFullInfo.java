package com.homework.comyno.nlf.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.homework.comyno.nlf.entities.Book;

public class BookFullInfo extends BookBaseInfo {

  private final StudentBaseInfo borrower;

  // Adding this to make integration testing deserialization easier
  @JsonCreator
  public BookFullInfo(
      @JsonProperty("isbn") String isbn,
      @JsonProperty("title") String title,
      @JsonProperty("borrower") StudentBaseInfo borrower) {
    super(isbn, title);
    this.borrower = borrower;
  }

  public BookFullInfo(Book book) {
    super(book);

    if (book.getLoan() != null) {
      this.borrower = new StudentBaseInfo(book.getLoan().getStudent());
    } else {
      borrower = null;
    }
  }

  public StudentBaseInfo getBorrower() {
    return borrower;
  }
}
