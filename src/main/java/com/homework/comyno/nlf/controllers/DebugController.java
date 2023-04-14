package com.homework.comyno.nlf.controllers;

import com.homework.comyno.nlf.api.BookFullInfo;
import com.homework.comyno.nlf.api.StudentFullInfo;
import com.homework.comyno.nlf.repositories.BookRepository;
import com.homework.comyno.nlf.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/debug")
public class DebugController {

  @Autowired BookRepository bookRepository;

  @Autowired StudentRepository studentRepository;

  @GetMapping("/books")
  public Iterable<BookFullInfo> getBooks() {
    return bookRepository.findAll().stream().map(BookFullInfo::new).toList();
  }

  @GetMapping("/students")
  public Iterable<StudentFullInfo> getStudents() {
    return studentRepository.findAll().stream().map(StudentFullInfo::new).toList();
  }
}
