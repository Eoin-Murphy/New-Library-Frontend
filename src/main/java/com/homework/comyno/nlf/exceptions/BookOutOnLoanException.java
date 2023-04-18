package com.homework.comyno.nlf.exceptions;

public class BookOutOnLoanException extends RuntimeException {
  public BookOutOnLoanException(String ISBN) {
    super(String.format("The book with ISBN %s is currently out on loan", ISBN));
  }
}
