package com.homework.comyno.nlf.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Loan {
  @Id private String id;

  private String book;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false)
  @JsonManagedReference
  private Student student;

  public Loan() {}

  public Loan(String id, String book, Student student) {
    this.id = id;
    this.book = book;
    this.student = student;
  }

  public String getId() {
    return id;
  }

  public String getBook() {
    return book;
  }

  public Student getStudent() {
    return student;
  }
}
