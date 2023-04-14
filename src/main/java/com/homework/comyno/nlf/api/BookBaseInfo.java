package com.homework.comyno.nlf.api;

import com.homework.comyno.nlf.entities.Book;

public class BookBaseInfo {

  private final String ISBN;
  private final String title;

  public BookBaseInfo(Book book) {
    this.ISBN = book.getISBN();
    this.title = book.getTitle();
  }

  public String getISBN() {
    return ISBN;
  }

  public String getTitle() {
    return title;
  }
}
