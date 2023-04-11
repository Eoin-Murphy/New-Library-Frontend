package com.homework.comyno.nlf.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

  @Id
  private int id;

  @Column(nullable=false)
  private String firstName;

  @Column(nullable=false)
  private String lastName;

  public Student(){}

  public Student(
      int id, String firstName, String lastName
  ){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
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
}
