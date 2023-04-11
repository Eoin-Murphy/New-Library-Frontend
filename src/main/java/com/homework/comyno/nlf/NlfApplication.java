package com.homework.comyno.nlf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.homework.comyno.nlf.entities")
@SpringBootApplication
public class NlfApplication {

	public static void main(String[] args) {
		SpringApplication.run(NlfApplication.class, args);
	}

}
