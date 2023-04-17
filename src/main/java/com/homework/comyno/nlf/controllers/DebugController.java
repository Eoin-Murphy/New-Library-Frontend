package com.homework.comyno.nlf.controllers;

import com.homework.comyno.nlf.api.BookFullInfo;
import com.homework.comyno.nlf.api.StudentFullInfo;
import com.homework.comyno.nlf.entities.Book;
import com.homework.comyno.nlf.entities.Loan;
import com.homework.comyno.nlf.entities.Student;
import com.homework.comyno.nlf.repositories.BookRepository;
import com.homework.comyno.nlf.repositories.LoanRepository;
import com.homework.comyno.nlf.repositories.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/debug")
public class DebugController {

  @Autowired BookRepository bookRepository;
  @Autowired StudentRepository studentRepository;
  @Autowired LoanRepository loanRepository;

  @PostMapping("/dbInit")
  public void init() {
    var book1 = new Book("ISBN-1", "Book 1", null);
    bookRepository.saveAll(
        List.of(book1, new Book("ISBN-2", "Book 2", null), new Book("ISBN-3", "Book 3", null)));

    var student1 = new Student("Student-1", "Alice", "Apple", null);
    studentRepository.saveAll(
        List.of(
            student1,
            new Student("Student-2", "Bob", "Busker", null),
            new Student("Student-3", "Clara", "Clarke", null)));

    loanRepository.save(new Loan("loan1", book1, student1));
  }

  @DeleteMapping("clearAll")
  public void clearAll() {
    loanRepository.deleteAll();

    bookRepository.deleteAll();
    studentRepository.deleteAll();
  }

  @GetMapping("/books")
  public Iterable<BookFullInfo> getBooks() {
    return bookRepository.findAll().stream().map(BookFullInfo::new).toList();
  }

  @GetMapping("/students")
  public Iterable<StudentFullInfo> getStudents() {
    return studentRepository.findAll().stream().map(StudentFullInfo::new).toList();
  }
}
