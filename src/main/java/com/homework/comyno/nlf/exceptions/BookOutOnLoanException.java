package com.homework.comyno.nlf.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BookOutOnLoanException extends RuntimeException {
  public BookOutOnLoanException(String ISBN){
    super(String.format("The book with ISBN %s is currently out on loan", ISBN));
  }
}
