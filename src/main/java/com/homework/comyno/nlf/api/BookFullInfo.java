package com.homework.comyno.nlf.api;

import com.homework.comyno.nlf.entities.Book;

public class BookFullInfo extends BookBaseInfo{

  private final StudentBaseInfo loanee;

  public BookFullInfo(Book book) {
    super(book);
    
    if(book.getLoan() != null){
      this.loanee = new StudentBaseInfo(book.getLoan().getStudent());
    } else {
      loanee = null;
    }
  }

  public StudentBaseInfo getLoanee() {
    return loanee;
  }
}
