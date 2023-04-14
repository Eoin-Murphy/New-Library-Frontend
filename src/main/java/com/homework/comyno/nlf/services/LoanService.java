package com.homework.comyno.nlf.services;

import com.homework.comyno.nlf.api.LoanRequest;
import com.homework.comyno.nlf.entities.Loan;
import com.homework.comyno.nlf.repositories.BookRepository;
import com.homework.comyno.nlf.repositories.LoanRepository;
import com.homework.comyno.nlf.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService{

  @Autowired LoanRepository loanRepository;
  @Autowired BookRepository bookRepository;
  @Autowired StudentRepository studentRepository;

  public Iterable<Loan> getAllLoans() {
    return loanRepository.findAll();
  }

  public void saveLoan(LoanRequest loan) {
    var book =
        bookRepository
            .findById(loan.getBookId())
            .orElseThrow(() -> new RuntimeException("Missing book"));
    var student =
        studentRepository
            .findById(loan.getStudentId())
            .orElseThrow(() -> new RuntimeException("Missing student"));

    loanRepository.save(new Loan(loan.getId(), book, student));
  }
}
