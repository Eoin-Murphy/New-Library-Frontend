package com.homework.comyno.nlf.repositories;

import com.homework.comyno.nlf.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {}
