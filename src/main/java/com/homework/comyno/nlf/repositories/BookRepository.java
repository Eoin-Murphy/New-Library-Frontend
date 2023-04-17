package com.homework.comyno.nlf.repositories;

import com.homework.comyno.nlf.entities.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
  Optional<Book> findByIsbn(String isbn);
}
