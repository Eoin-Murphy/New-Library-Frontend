package com.homework.comyno.nlf.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.homework.comyno.nlf.entities.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentFullInfo extends StudentBaseInfo {

  private final List<BookBaseInfo> borrowedBooks;

  // Adding this to make integration testing deserialization easier
  @JsonCreator
  public StudentFullInfo(
      @JsonProperty("id") String id,
      @JsonProperty("firstName") String firstName,
      @JsonProperty("lastName") String lastName,
      @JsonProperty("borrowedBooks") List<BookBaseInfo> borrowedBooks) {
    super(id, firstName, lastName);
    this.borrowedBooks = borrowedBooks;
  }

  public StudentFullInfo(Student student) {
    super(student);

    if (student.getLoans().isEmpty()) {
      borrowedBooks = new ArrayList<>();
    } else {
      borrowedBooks =
          student.getLoans().stream().map((loan) -> new BookBaseInfo(loan.getBook())).toList();
    }
  }

  public List<BookBaseInfo> getBorrowedBooks() {
    return borrowedBooks;
  }
}
