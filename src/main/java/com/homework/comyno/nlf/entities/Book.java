package com.homework.comyno.nlf.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Book {
  @Id private String ISBN;

  @Column(nullable = false)
  private String title;

  @OneToOne(mappedBy = "book")
  @JoinColumn(name="loan_id", referencedColumnName = "id")
  @JsonBackReference
  private Loan loan;

  public Book() {}

  public Book(String ISBN, String title, Loan loan) {
    this.ISBN = ISBN;
    this.title = title;
    this.loan = loan;
  }

  public String getISBN() {
    return ISBN;
  }

  public String getTitle() {
    return title;
  }

  public Loan getLoan(){
    return this.loan;
  }
}
