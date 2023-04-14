package com.homework.comyno.nlf.api;

public class LoanRequest {

  private final String id;
  private final String bookId;
  private final String studentId;

  public LoanRequest(String id, String bookId, String studentId) {
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
