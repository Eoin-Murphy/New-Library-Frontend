package com.homework.comyno.nlf.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoanRequest {

  private final String id;
  private final String bookId;
  private final String studentId;

  @JsonCreator
  public LoanRequest(
      @JsonProperty("id") String id,
      @JsonProperty("bookId") String bookId,
      @JsonProperty("studentId") String studentId) {
    this.id = id;
    this.bookId = bookId;
    this.studentId = studentId;
  }

  public String getId() {
    return id;
  }

  public String getBookId() {
    return bookId;
  }

  public String getStudentId() {
    return studentId;
  }
}
