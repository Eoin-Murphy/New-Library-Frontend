package com.homework.comyno.nlf.controllers;

import com.homework.comyno.nlf.api.LoanInfo;
import com.homework.comyno.nlf.api.LoanRequest;
import com.homework.comyno.nlf.exceptions.BookOutOnLoanException;
import com.homework.comyno.nlf.exceptions.EntityNotFoundException;
import com.homework.comyno.nlf.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    try{
      loanService.saveLoan(loan);
    }
    catch(BookOutOnLoanException ex){
      throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
    }
    catch(EntityNotFoundException ex){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

    }

    return getLoans();
  }
}
