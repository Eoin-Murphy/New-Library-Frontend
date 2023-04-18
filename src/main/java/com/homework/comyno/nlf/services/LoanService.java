package com.homework.comyno.nlf.services;

import com.homework.comyno.nlf.api.LoanRequest;
import com.homework.comyno.nlf.api.ReturnRequest;
import com.homework.comyno.nlf.entities.Loan;
import com.homework.comyno.nlf.exceptions.BookNotOutOnLoanException;
import com.homework.comyno.nlf.exceptions.BookOutOnLoanException;
import com.homework.comyno.nlf.exceptions.EntityNotFoundException;
import com.homework.comyno.nlf.repositories.BookRepository;
import com.homework.comyno.nlf.repositories.LoanRepository;
import com.homework.comyno.nlf.repositories.StudentRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

  @Autowired LoanRepository loanRepository;
  @Autowired BookRepository bookRepository;
  @Autowired StudentRepository studentRepository;

  public List<Loan> getAllLoans() {
    return loanRepository.findAll();
  }

  public void bookLoanRequested(LoanRequest loan) {
    var book =
        bookRepository
            .findByIsbn(loan.getIsbn())
            .orElseThrow(() -> new EntityNotFoundException("book", loan.getIsbn()));
    var student =
        studentRepository
            .findById(loan.getStudentId())
            .orElseThrow(() -> new EntityNotFoundException("student", loan.getStudentId()));

    if (loanRepository.findByBookIsbn(loan.getIsbn()).isPresent()) {
      throw new BookOutOnLoanException(loan.getIsbn());
    }

    loanRepository.save(new Loan(UUID.randomUUID().toString(), book, student));
  }

  public void bookReturnRequested(ReturnRequest returnRequest) {
    var book =
        bookRepository
            .findByIsbn(returnRequest.getIsbn())
            .orElseThrow(() -> new EntityNotFoundException("book", returnRequest.getIsbn()));
    var student =
        studentRepository
            .findById(returnRequest.getStudentId())
            .orElseThrow(
                () -> new EntityNotFoundException("student", returnRequest.getStudentId()));

    var loan =
        loanRepository
            .findByBookIsbn(returnRequest.getIsbn())
            .orElseThrow(() -> new BookNotOutOnLoanException(returnRequest.getIsbn()));

    loanRepository.deleteById(loan.getId());
  }
}
