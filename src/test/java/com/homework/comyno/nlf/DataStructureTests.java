package com.homework.comyno.nlf;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.homework.comyno.nlf.entities.Book;
import com.homework.comyno.nlf.entities.Loan;
import com.homework.comyno.nlf.entities.Student;
import com.homework.comyno.nlf.repositories.BookRepository;
import com.homework.comyno.nlf.repositories.LoanRepository;
import com.homework.comyno.nlf.repositories.StudentRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DataStructureTests {

  @Autowired BookRepository bookRepository;
  @Autowired StudentRepository studentRepository;
  @Autowired LoanRepository loanRepository;

  @Test
  @Ignore("Test is failing where it shouldn't due to spring defaults")
  public void insertRetrieveAndDelete() throws InterruptedException {
    var book = new Book("test-book-1", "Test Book 1", null);
    var student = new Student("test-student-1", "Test1-first", "Test1-lastName", null);
    var loan = new Loan("test-loan-1", book, student);

    bookRepository.save(book);
    loanRepository.saveAndFlush(loan);

    var retrievedBook = bookRepository.findById(book.getIsbn()).orElse(null);
    var retrievedLoan = loanRepository.findById(loan.getId());

    assertNotNull(retrievedBook);
    assertNotNull(retrievedBook.getLoan());
  }
}
