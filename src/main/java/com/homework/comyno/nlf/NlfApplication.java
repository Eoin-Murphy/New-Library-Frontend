package com.homework.comyno.nlf;

import com.homework.comyno.nlf.entities.Book;
import com.homework.comyno.nlf.entities.Student;
import com.homework.comyno.nlf.repositories.BookRepository;
import com.homework.comyno.nlf.repositories.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.homework.comyno.nlf.repositories")
@EntityScan("com.homework.comyno.nlf.entities")
@SpringBootApplication
public class NlfApplication {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(NlfApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			initializeDB();
		};
	}

	private void initializeDB(){
		bookRepository.saveAll(
				List.of(
						new Book("ISBN--1", "Book 1"),
						new Book("ISBN--2", "Book 2"),
						new Book("ISBN--3", "Book 3")));

		studentRepository.saveAll(
				List.of(
						new Student(1, "Alice", "Apple"),
						new Student(2, "Bob", "Busker"),
						new Student(3, "Clara", "Clarke")));

	}
}
