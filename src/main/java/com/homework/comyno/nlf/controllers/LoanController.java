package com.homework.comyno.nlf.controllers;

import com.homework.comyno.nlf.api.LoanInfo;
import com.homework.comyno.nlf.api.LoanRequest;
import com.homework.comyno.nlf.api.ReturnRequest;
import com.homework.comyno.nlf.exceptions.BookNotOutOnLoanException;
import com.homework.comyno.nlf.exceptions.BookOutOnLoanException;
import com.homework.comyno.nlf.exceptions.EntityNotFoundException;
import com.homework.comyno.nlf.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

  @Autowired LoanService loanService;

  @GetMapping
  public Iterable<LoanInfo> getAllBooksOnLoan() {
    return loanService.getAllLoans().stream().map(LoanInfo::new).toList();
  }

  @PostMapping
  public Iterable<LoanInfo> loanBook(@RequestBody LoanRequest loan) {
    try {
      loanService.bookLoanRequested(loan);
    } catch (BookOutOnLoanException ex) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
    } catch (EntityNotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    return getAllBooksOnLoan();
  }
  
  @DeleteMapping
  public Iterable<LoanInfo> returnBook(@RequestBody ReturnRequest returnRequest){
    try {
      loanService.bookReturnRequested(returnRequest);
    } catch (EntityNotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    } catch (BookNotOutOnLoanException ex){
      throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
    }

    return getAllBooksOnLoan();

  }
}
