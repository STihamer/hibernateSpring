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

@Repository
@Transactional
public class ReviewRepository {
    @Autowired
    private EntityManager em;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Review save(Review review) {
        if (review.getId() == null) {
            em.persist(review);
        } else {
            em.merge(review);
        }
        return review;
    }

    public Review findById(Long id) {
        return em.find(Review.class, id);
    }

    public void deleteById(Long id) {
        Review review = findById(id);
        em.remove(review);
    }

    public void playWithEntityManager() {
        Review review1 = new Review(ReviewRating.FIVE, "aaaaa");
        em.persist(review1);
        Review review2 = findById(2L);
        em.persist(review2);
        review2.setDescription("");
        em.flush();

        //em.detach(course2); will not allow changes in the specific course
        //m.clear(); //will not allow further changes in both courses

        review1.setDescription("Entity manager updated");
       // course2.setName("Angular Js in 100 steps updated");

        //em.refresh(course1); //we will get the data from the database without changes
        em.flush();

    }
}
