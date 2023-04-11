package com.homework.comyno.nlf.repositories;

import com.homework.comyno.nlf.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {}
