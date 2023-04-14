package com.homework.comyno.nlf.api;

import com.homework.comyno.nlf.entities.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentFullInfo extends StudentBaseInfo {

  private final List<BookBaseInfo> loanedBooks;

  public StudentFullInfo(Student student) {
    super(student);

    if (student.getLoans().isEmpty()) {
      loanedBooks = new ArrayList<>();
    } else {
      loanedBooks =
          student.getLoans().stream().map((loan) -> new BookBaseInfo(loan.getBook())).toList();
    }
  }

  public List<BookBaseInfo> getLoanedBooks() {
    return loanedBooks;
  }
}
