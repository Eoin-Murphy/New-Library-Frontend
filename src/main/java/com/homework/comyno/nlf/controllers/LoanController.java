package com.homework.comyno.nlf.controllers;

import com.homework.comyno.nlf.api.LoanInfo;
import com.homework.comyno.nlf.api.LoanRequest;
import com.homework.comyno.nlf.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

  @Autowired
  LoanService loanService;

  @GetMapping
  public Iterable<LoanInfo> getLoans() {
    return loanService.getAllLoans().stream().map(LoanInfo::new).toList();
  }

  @PostMapping
  public Iterable<LoanInfo> postLoan(@RequestBody LoanRequest loan) {
    loanService.saveLoan(loan);
    return getLoans();
  }
}
