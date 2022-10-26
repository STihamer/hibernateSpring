package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class NativeQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;


    @Test
    void native_queries_basic() {
        Query query = em.createNativeQuery("select * from course where is_deleted=false", Course.class);
        List resultList = query.getResultList();
        logger.info("Select * from Course  -> {}", resultList);
    }

    @Test
    void native_queries_with_parameters() {
        Query query = em.createNativeQuery("select * from course where id = ?", Course.class);
        query.setParameter(1, 2);
        List resultList = query.getResultList();
        logger.info("Selected course  -> {}", resultList);
    }

    @Test
    void native_queries_with_named_parameter() {
        Query query = em.createNativeQuery("select * from course where id = :id", Course.class);
        query.setParameter("id", 3);
        List resultList = query.getResultList();
        logger.info("Selected course by named parameter  -> {}", resultList);
    }

    @Test
    @Transactional
    void native_queries_to_update() {
        Query query = em.createNativeQuery("Update course set last_updated_date=curdate()", Course.class);
        int noOfRowsUpdated = query.executeUpdate();
        logger.info("Updated all last updated date  -> {}", noOfRowsUpdated);
    }
}