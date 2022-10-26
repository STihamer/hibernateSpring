package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void all_courses() {
        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = cb.createQuery(Course.class);

        //2 Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);//this is the root of the query


        //3 Define Predicates etc using Criteria Builder

        //4 Add predicates etc to the Criteria Query

        //5 Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
        logger.info("Typed Query -> {}", resultList.get(2).getStudents());
    }

    @Test
    @Transactional
    void all_courses_having_100Steps() {

        //Select c From course c where name like '%10%'
        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = cb.createQuery(Course.class);

        //2 Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);//this is the root of the query


        //3 Define Predicates etc using Criteria Builder
        Predicate like = cb.like(courseRoot.get("name"), "%50%");

        //4 Add predicates etc to the Criteria Query
        criteriaQuery.where(like);

        //5 Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    @Transactional
    void all_courses_withoutStudents() {

        //Select c From course c where c.students is empty()
        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = cb.createQuery(Course.class);

        //2 Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);//this is the root of the query


        //3 Define Predicates etc using Criteria Builder
        Predicate studentIsEmpty = cb.isEmpty(courseRoot.get("students"));

        //4 Add predicates etc to the Criteria Query

        criteriaQuery.where(studentIsEmpty);

        //5 Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    @Transactional
    void join() {

        //Select c From Course c join c.students
        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = cb.createQuery(Course.class);

        //2 Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);//this is the root of the query


        //3 Define Predicates etc using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students");

        //4 Add predicates etc to the Criteria Query


        //5 Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    @Transactional
    void left_join() {

        //Select c From course c where c.students is empty()
        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = cb.createQuery(Course.class);

        //2 Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);//this is the root of the query


        //3 Define Predicates etc using Criteria Builder
       Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

        //4 Add predicates etc to the Criteria Query


        //5 Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }
}
