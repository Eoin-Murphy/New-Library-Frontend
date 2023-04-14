package com.homework.comyno.nlf.api;

import com.homework.comyno.nlf.entities.Loan;

public class LoanInfo {
  private final String id;
  private final BookBaseInfo book;
  private final StudentBaseInfo student;

  public LoanInfo(Loan loan) {
    this.id = loan.getId();
    this.book = new BookBaseInfo(loan.getBook());
    this.student = new StudentBaseInfo(loan.getStudent());
  }

  public String getId() {
    return id;
  }

  public BookBaseInfo getBook() {
    return book;
  }

  public StudentBaseInfo getStudent() {
    return student;
  }
}
