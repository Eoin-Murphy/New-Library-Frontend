package com.homework.comyno.nlf.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.homework.comyno.nlf.entities.Student;

public class StudentBaseInfo {

  private final String id;
  private final String firstName;
  private final String lastName;

  // Adding this to make integration testing deserialization easier
  @JsonCreator
  public StudentBaseInfo(
      @JsonProperty("id") String id,
      @JsonProperty("firstName") String firstName,
      @JsonProperty("lastName") String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

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
