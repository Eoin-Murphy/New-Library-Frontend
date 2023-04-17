package com.homework.comyno.nlf.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Loan {
  @Id private String id;

  @OneToOne()
  @JoinColumn(name = "book_id", referencedColumnName = "ISBN")
  @JsonManagedReference
  private Book book;

  @ManyToOne()
  @JoinColumn(name = "student_id", referencedColumnName = "id")
  @JsonManagedReference
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
