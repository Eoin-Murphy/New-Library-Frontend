package com.homework.comyno.nlf.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Book {
  @Id private String ISBN;

  @Column(nullable = false)
  private String title;

  @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
  @JsonBackReference
  private Loan loan;

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
