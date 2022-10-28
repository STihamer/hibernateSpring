package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import com.in28minutes.jpa.hibernate.demo.entity.ReviewRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {
    @Autowired
    EntityManager em;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public void playWithEntityManager() {
        Course course1 = new Course("Entity Manager");
        em.persist(course1);
        Course course2 = findById(2L);
        em.persist(course2);
        course2.setName("Jpa changing");
        em.flush();

        //em.detach(course2); will not allow changes in the specific course
        //m.clear(); //will not allow further changes in both courses

        course1.setName("Entity manager updated");
        // course2.setName("Angular Js in 100 steps updated");

        //em.refresh(course1); //we will get the data from the database without changes
        em.flush();

    }

    public void addHardCodedReviewsForCourse() {
        //get the course 10003
        //add 2 reviews to it
        //save it to the database
        Course course = findById(10003L);
        logger.info("{course.getReviews() -> {}", course.getReviews());
        Review review1 = new Review(50004L, ReviewRating.FIVE, "Great hands-on stuff.");
        Review review2 = new Review(50005L, ReviewRating.FOUR, "Hatsoff.");


        //setting the relationship
        course.addReview(review1);
        review1.setCourse(course);
        course.addReview(review2);
        review2.setCourse(course);

        em.persist(review1);
        em.persist(review2);

        logger.info("{course.getReviews() -> {}", course.getReviews());
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        logger.info("{course.getReviews() -> {}", course.getReviews());

        for (Review review : reviews) {

            course.addReview(review);
            review.setCourse(course);

            em.persist(review);
        }
    }
}
