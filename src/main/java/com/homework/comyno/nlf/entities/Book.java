package com.homework.comyno.nlf.entities;

import jakarta.persistence.*;

@Entity
public class Book {
  @Id private String isbn;

  @Column(nullable = false)
  private String title;

  @OneToOne(mappedBy = "book")
  @JoinColumn(name = "loan_id", referencedColumnName = "id")
  private Loan loan;

  public Book() {}

  public Book(String isbn, String title, Loan loan) {
    this.isbn = isbn;
    this.title = title;
    this.loan = loan;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public Loan getLoan() {
    return this.loan;
  }
}
