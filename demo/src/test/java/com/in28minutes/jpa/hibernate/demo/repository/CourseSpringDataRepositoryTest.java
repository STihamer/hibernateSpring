package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CourseSpringDataRepository courseSpringDataRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void findById_CoursePresent() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(10001L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    void findById_CourseNotPresent() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(20001L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    void playingAroundWithSpringDataRepository() {
        Course course = new Course("Microservices in 100 Steps");
        courseSpringDataRepository.save(course);
        course.setName("Microservices in 100 Steps - - Updated");
        courseSpringDataRepository.save(course);

        logger.info("Courses -> {}", courseSpringDataRepository.findAll());
        logger.info("Courses -> {}", courseSpringDataRepository.count());
    }

    @Test
    void sort() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");

        logger.info(" Sorted Courses -> {}", courseSpringDataRepository.findAll(sort));
        logger.info("Courses -> {}", courseSpringDataRepository.count());
    }

    @Test
    void pagination() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Course> firstPage = courseSpringDataRepository.findAll(pageRequest);

        logger.info(" First Page -> {}", firstPage.getContent());
        logger.info(" First Page -> {}", firstPage.getNumberOfElements());
        logger.info("Courses -> {}", courseSpringDataRepository.count());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = courseSpringDataRepository.findAll(secondPageable);
        logger.info(" Second Page -> {}", secondPage.getContent());
        logger.info(" Second Page -> {}", secondPage.getNumberOfElements());

        Pageable thirdPageable = secondPage.nextPageable();
        Page<Course> thirdPage = courseSpringDataRepository.findAll(thirdPageable);
        logger.info(" Third Page -> {}", thirdPage.getContent());
        logger.info(" Third Page -> {}", thirdPage.getNumberOfElements());
    }

    @Test
    public void findUsingName(){
        logger.info("Find by name -> {}", courseSpringDataRepository.findByName("Jpa 140 steps"));
    }

    @Test
    public void findByNameOrderByIdDesc(){
        logger.info("Find by name  order by id desc-> {}", courseSpringDataRepository.findByNameOrderByIdDesc("Jpa 140 steps"));
    }

    @Test
    public void coursesWithStepsInNameUsingNativeQuery(){
        logger.info("Find by name  order by id desc-> {}", courseSpringDataRepository.coursesWithStepsInNameUsingNativeQuery());
    }
}
