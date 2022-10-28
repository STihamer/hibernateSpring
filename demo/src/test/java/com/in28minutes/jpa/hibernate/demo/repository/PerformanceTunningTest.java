package com.in28minutes.jpa.hibernate.demo.repository;


import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.hibernate.graph.SubGraph;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class PerformanceTunningTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager entityManager;


    @Test
    @Transactional
    public void creatingNPlusOneProblem() {
        List<Course> courses = entityManager.createNamedQuery("query_get_all_courses", Course.class).getResultList();
        for (Course course : courses) {
            logger.info("Course -> {}, Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_EntityGraphSolution() {
        EntityGraph<Course> entityGraph = entityManager.createEntityGraph(Course.class);
        Subgraph<Object> subGraph = entityGraph.addSubgraph("students");
        List<Course> courses = entityManager
                .createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        for (Course course : courses) {
            logger.info("Course -> {}, Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_JoinFetch() {

        List<Course> courses = entityManager
                .createNamedQuery("query_get_all_courses_join_fetch", Course.class)
                .getResultList();

        for (Course course : courses) {
            logger.info("Course -> {}, Students -> {}", course, course.getStudents());
        }
    }

}