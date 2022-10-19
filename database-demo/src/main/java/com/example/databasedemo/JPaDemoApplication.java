
package com.example.databasedemo;

import com.example.databasedemo.entity.Person;
import com.example.databasedemo.jpa.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class JPaDemoApplication implements CommandLineRunner {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJpaRepository personJpaRepository;

    public static void main(String[] args) {
        SpringApplication.run(JPaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Inserting -> {}", personJpaRepository.insert(new Person("Tara", "Berlin",
                new Date())));
        logger.info("Inserting -> {}", personJpaRepository.insert(new Person("Joska",
                "Lovete", new Date())));
        logger.info("Inserting  -> {}",
                personJpaRepository.insert(new Person("Marci", "Iasi", new Date())));
        logger.info("User id  -> {}", personJpaRepository.findById(3));

        logger.info("Inserting -> {}", personJpaRepository.update(new Person(1,"Tara", "Marefalva",
                new Date())));
        logger.info("User id  -> {}", personJpaRepository.findById(1));

        personJpaRepository.deleteById(1);
        logger.info("All users -> {}", personJpaRepository.findAll());


    }
}
