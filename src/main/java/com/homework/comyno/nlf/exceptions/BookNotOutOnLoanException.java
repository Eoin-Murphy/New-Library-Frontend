package com.homework.comyno.nlf.exceptions;

public class BookNotOutOnLoanException extends RuntimeException {
  public BookNotOutOnLoanException(String ISBN) {
    super(String.format("The book with ISBN %s is currently not out on loan", ISBN));
  }
}
