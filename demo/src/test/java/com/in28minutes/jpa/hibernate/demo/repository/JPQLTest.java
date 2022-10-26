package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class)

class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;

    @Test
    void jpql_basic() {
        Query query  = em.createNamedQuery("query_get_all_courses");
        List resultList = query.getResultList();
        logger.info("Select c from Course c -> {}", resultList);
    }

    @Test
    void jpql_typed() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c -> {}", resultList);
    }

    @Test
    void jpql_where() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_100", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course where name like '%' -> {}", resultList);
    }

    @Test
    @Transactional
    void jpql_courses_without_students() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }

    @Test
    @Transactional
    void jpql_courses_with_atLeast_2_student() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }

    @Test
    @Transactional
    public void jpql_courses_ordered_by_students() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c order by c.students.size", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }


    @Test
    @Transactional
    void jpql_students_with_passports_in_a_certain_pattern() {
        TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%RO%'",
                Student.class);
        List<Student> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }

    @Test
    @Transactional
    public void join(){
        Query query = em.createQuery("Select c,s from Course c join c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size-> {}", resultList.size());

        for(Object[] result:resultList){
            logger.info("Course{}, Student...........{}",result[0],result[1] );
             // course

        }
    }


    @Test
    @Transactional
    public void left_join(){
        Query query = em.createQuery("Select c,s from Course c  left join c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size-> {}", resultList.size());

        for(Object[] result:resultList){
            logger.info("Course{}, Student...........{}",result[0],result[1] );
            // course

        }
    }

    @Test
    @Transactional
    public void cross_join(){
        Query query = em.createQuery("Select c, s from Course c,  Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size-> {}", resultList.size());

        for(Object[] result:resultList){
            logger.info("Course{}, Student...........{}",result[0],result[1] );
            // course

        }
    }

}