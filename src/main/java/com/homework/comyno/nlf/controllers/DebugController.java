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

  // exposing these to make testing easier
  public static final String isbn1 = "isbn1";
  public static final String isbn2 = "isbn2";
  public static final String isbn3 = "isbn3";

  public static final String studentId1 = "student1";
  public static final String studentId2 = "student2";
  public static final String studentId3 = "student3";

  public static final String loanId1 = "loan1";

  @SuppressWarnings("OptionalGetWithoutIsPresent")
  @PostMapping("/dbInit")
  public void init() {
    bookRepository.saveAll(
        List.of(
            new Book(isbn1, "Book 1", null),
            new Book(isbn2, "Book 2", null),
            new Book(isbn3, "Book 3", null)));

    studentRepository.saveAll(
        List.of(
            new Student(studentId1, "Alice", "Apple", null),
            new Student(studentId2, "Bob", "Busker", null),
            new Student(studentId3, "Clara", "Clarke", null)));

    var book1 = bookRepository.findByIsbn(isbn1).get();
    var student1 = studentRepository.findById(studentId1).get();

    loanRepository.save(new Loan(loanId1, book1, student1));
  }

  @DeleteMapping("clearAll")
  public void clearAll() {
    loanRepository.deleteAll(); // this must be called first due to dependencies

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
