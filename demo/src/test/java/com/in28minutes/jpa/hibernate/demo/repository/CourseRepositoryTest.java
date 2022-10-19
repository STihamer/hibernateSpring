package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
        courseRepository.deleteById(2L);
        assertNull(courseRepository.findById(1L));
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
    public void retrieveReviewsForCourse(){
        Course course1 = courseRepository.findById(10001L);
        logger.info("course1.getReviews -> {}" , course1.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseForReview(){
        Review review = entityManager.find(Review.class,50001L);
        logger.info("review.getCourse -> {}" , review.getCourse());
    }
}