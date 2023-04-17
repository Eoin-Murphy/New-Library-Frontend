package com.homework.comyno.nlf.controllers;

import com.homework.comyno.nlf.api.LoanRequest;
import com.homework.comyno.nlf.entities.Loan;
import com.homework.comyno.nlf.repositories.BookRepository;
import com.homework.comyno.nlf.repositories.LoanRepository;
import com.homework.comyno.nlf.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

  @Autowired LoanRepository loanRepository;
  @Autowired StudentRepository studentRepository;
  @Autowired BookRepository bookRepository;

  @GetMapping("/")
  public Iterable<Loan> getLoans() {
    return loanRepository.findAll();
  }

  @PostMapping("/")
  public Iterable<Loan> postLoan(@RequestBody LoanRequest loan) {
    var book =
        bookRepository
            .findById(loan.getBookId())
            .orElseThrow(() -> new RuntimeException("Missing book"));
    var student =
        studentRepository
            .findById(loan.getStudentId())
            .orElseThrow(() -> new RuntimeException("Missing student"));

    loanRepository.save(new Loan(loan.getId(), book, student));

    return getLoans();
  }
}
