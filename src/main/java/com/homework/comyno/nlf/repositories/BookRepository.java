package com.homework.comyno.nlf.repositories;

import com.homework.comyno.nlf.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {}
