package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void findById() {
        Course course = courseRepository.findById(2L);

        logger.info("Test is running");
        assertEquals("MIcroservices in 1001 steps", course.getName());

    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        courseRepository.deleteById(10002L);
        assertNull(courseRepository.findById(10002L));
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        //get a course
        Course course = courseRepository.findById(2L);
        assertEquals("MIcroservices in 1001 steps", course.getName());

        //update details
        course.setName("MIcroservices in 1001 steps updated");
        courseRepository.save(course);

        //check the value
        Course course1 = courseRepository.findById(2L);
        assertEquals("MIcroservices in 1001 steps updated", course1.getName());
    }

    @Test
    @DirtiesContext
    public void playWithEntityManager() {
        //get a course
        courseRepository.playWithEntityManager();

    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse() {
        Course course1 = courseRepository.findById(10001L);
        logger.info("course1.getReviews -> {}", course1.getReviews());
    }

    @Test
    @Transactional(isolation = Isolation.DEFAULT)
    public void retrieveCourseForReview() {
        Review review = entityManager.find(Review.class, 50001L);
        logger.info("review.getCourse -> {}", review.getCourse());
    }

    @Test
    @Transactional
    public void findById_firstLevelCacheDemo() {
        Course course = courseRepository.findById(10001L);
        logger.info("First Course Retrieved {}", course);

        Course course1 = courseRepository.findById(10001L);  //this is first level cache
        logger.info("First Course Retrieved again  {}", course1);

        assertEquals("Jpa", course.getName());
        assertEquals("Jpa", course1.getName());
    }


}