package com.homework.comyno.nlf.repositories;

import com.homework.comyno.nlf.entities.Loan;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, String> {
  Optional<Loan> findByBookIsbn(String isbn);
}
