package com.homework.comyno.nlf.api;

import com.homework.comyno.nlf.entities.Student;

public class StudentBaseInfo {

  private final String id;

  private final String firstName;
  private final String lastName;
  public StudentBaseInfo(Student student) {
    this.id = student.getId();
    this.firstName = student.getFirstName();
    this.lastName = student.getLastName();
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
}
