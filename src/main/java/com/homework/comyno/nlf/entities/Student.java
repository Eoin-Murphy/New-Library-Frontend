package com.homework.comyno.nlf.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Student {

  @Id private String id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @OneToMany(mappedBy = "student")
  private List<Loan> loans;

  public Student() {}

  public Student(String id, String firstName, String lastName, List<Loan> loans) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.loans = loans;
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public List<Loan> getLoans() {
    return loans;
  }
}
