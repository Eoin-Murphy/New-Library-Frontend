package com.homework.comyno.nlf.api;

public class LoanRequest {

  private final String isbn;
  private final String studentId;

  public LoanRequest(String isbn, String studentId) {
    this.isbn = isbn;
    this.studentId = studentId;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getStudentId() {
    return studentId;
  }
}
