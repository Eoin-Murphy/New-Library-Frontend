package com.homework.comyno.nlf;

import com.homework.comyno.nlf.repositories.BookRepository;
import com.homework.comyno.nlf.repositories.LoanRepository;
import com.homework.comyno.nlf.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.homework.comyno.nlf.repositories")
@EntityScan("com.homework.comyno.nlf.entities")
@SpringBootApplication
public class NlfApplication {

  @Autowired BookRepository bookRepository;

  @Autowired StudentRepository studentRepository;

  @Autowired LoanRepository loanRepository;

  public static void main(String[] args) {
    SpringApplication.run(NlfApplication.class, args);
  }
}
