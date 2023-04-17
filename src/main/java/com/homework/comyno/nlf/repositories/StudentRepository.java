package com.homework.comyno.nlf.repositories;

import com.homework.comyno.nlf.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {}
