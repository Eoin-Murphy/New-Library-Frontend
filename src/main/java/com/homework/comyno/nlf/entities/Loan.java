package com.homework.comyno.nlf.entities;

import jakarta.persistence.*;

@Entity
public class Loan {
  @Id
  private String id;

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "book_isbn", referencedColumnName = "isbn")
  private Book book;

  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "student_id", referencedColumnName = "id")
  private Student student;

  public Loan() {}

  public Loan(String id, Book book, Student student) {
    this.id = id;
    this.book = book;
    this.student = student;
  }

  public String getId() {
    return id;
  }

  public Book getBook() {
    return book;
  }

  public Student getStudent() {
    return student;
  }
}
