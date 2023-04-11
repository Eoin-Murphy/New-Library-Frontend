package com.homework.comyno.nlf.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Student {

  @Id private int id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
  @JsonBackReference
  private List<Loan> loans;

  public Student() {}

  public Student(int id, String firstName, String lastName, List<Loan> loans) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.loans = Objects.requireNonNullElse(loans, new ArrayList<>());
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public List<Loan> getLoans(){ return  loans;}
}
