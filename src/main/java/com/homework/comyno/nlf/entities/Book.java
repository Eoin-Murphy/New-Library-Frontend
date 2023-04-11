package com.homework.comyno.nlf.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {
  @Id private String ISBN;

  @Column(nullable = false)
  private String title;

  public Book() {}

  public Book(String ISBN, String title) {
    this.ISBN = ISBN;
    this.title = title;
  }

  public String getISBN() {
    return ISBN;
  }

  public String getTitle() {
    return title;
  }
}
