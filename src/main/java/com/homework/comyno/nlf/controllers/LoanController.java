package com.homework.comyno.nlf.controllers;

import com.homework.comyno.nlf.entities.Loan;
import com.homework.comyno.nlf.repositories.LoanRepository;
import com.homework.comyno.nlf.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

  @Autowired LoanRepository loanRepository;
  @Autowired StudentRepository studentRepository;

  @GetMapping("/")
  public Iterable<Loan> getLoans() {
    return loanRepository.findAll();
  }
}
