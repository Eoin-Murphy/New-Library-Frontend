package com.homework.comyno.nlf.api;

import com.homework.comyno.nlf.entities.Book;

public class BookResponse {

  private final String ISBN;
  private final String title;
  private final StudentInfo loanee;

  public BookResponse(Book book) {
    this.ISBN = book.getISBN();
    this.title = book.getTitle();
    if(book.getLoan() != null){
      this.loanee = new StudentInfo(book.getLoan().getStudent());
    } else {
      loanee = null;
    }
  }

  public String getISBN() {
    return ISBN;
  }


  public String getTitle() {
    return title;
  }

  public StudentInfo getLoanee() {
    return loanee;
  }
}
