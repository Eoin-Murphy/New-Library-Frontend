package com.homework.comyno.nlf.repositories;

import com.homework.comyno.nlf.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, String> {}
