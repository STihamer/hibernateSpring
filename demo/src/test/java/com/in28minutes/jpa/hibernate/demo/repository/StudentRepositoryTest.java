package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Address;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager entityManager;

    @Transactional
    @Test
    void retrieveStudentAndPassport() {
        Student student = entityManager.find(Student.class, 20001L);
        logger.info("student.................. -> {}", student);
        //logger.info("passport............ -> {}", student.getPassport());


    }

    @Transactional
    @Test
    void retrieveStudentAndPassportDetails() {

        Student student = entityManager.find(Student.class, 20001L);
        logger.info("student.................. -> {}", student);
        //logger.info("passport............ -> {}", student.getPassport());


    }


    @Test
    @Transactional
        //Persistence context
    void someTest() {
        //Database operation 1 - Retrieve student
        Student student = entityManager.find(Student.class, 20001L);
        //Persistence context - student
        //Database Operation 2 - Retrieve passport
        Passport passport = student.getPassport();
        //Persistence context - student, passport
        // entityManager.flush will push all the changes made until this point to the database
        //Database operation 3 - update passport
        passport.setNumber("333333333");
        //Persistence context - student, passport++

        //Database OPeration 4 - update student
        student.setName("Ranga -updated");
        //Persistence context - student++, passport++

        //we interact with persistence context is by using an entity manager
    }//all changes are saved down to the database -hibernate waits the last possible moment before save down all
    // changes in database

    @Transactional
    @Test
    void retrievePassportAndAssociatedStudent() {

        Passport passport = entityManager.find(Passport.class, 40001L);
        logger.info("passport.................. -> {}", passport);
        logger.info("student............ -> {}", passport.getStudent());


    }

    @Transactional
    @Test
    void retrieveStudentAndCourses() {
        Student student = entityManager.find(Student.class, 20001L);

        logger.info("student.................... -> {} ", student);
        logger.info("courses............................ -> {} ", student.getCourses());

    }

    @Transactional
    @Test
    void setAddressDetails() {
        Student student = entityManager.find(Student.class, 20001L);
        student.setAddress(new Address("No 101", "Some street", "Hyderabad"));
        entityManager.flush();
        logger.info("student.................... -> {} ", student.getAddress());
        logger.info("courses............................ -> {} ", student.getCourses());

    }



}