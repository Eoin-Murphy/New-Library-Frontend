package com.homework.comyno.nlf.entities;

import jakarta.persistence.*;

@Entity
public class Book {
  @Id private String isbn;

  @Column(nullable = false)
  private String title;

  public Book() {}

  public Book(String isbn, String title) {
    this.isbn = isbn;
    this.title = title;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }
}
