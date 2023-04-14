package com.homework.comyno.nlf.controllers;

import com.homework.comyno.nlf.api.BookResponse;
import com.homework.comyno.nlf.entities.Student;
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
  public Iterable<BookResponse> getBooks() {
    return bookRepository.findAll().stream().map(BookResponse::new).toList();
  }

  @GetMapping("/students")
  public Iterable<Student> getStudents() {
    return studentRepository.findAll();
  }
}
