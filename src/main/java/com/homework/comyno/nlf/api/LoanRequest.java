package com.homework.comyno.nlf.api;

public class LoanRequest {

  private final String id;
  private final String isbn;
  private final String studentId;

  public LoanRequest(String id, String isbn, String studentId) {
    this.id = id;
    this.isbn = isbn;
    this.studentId = studentId;
  }

  public String getId() {
    return id;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getStudentId() {
    return studentId;
  }
}
