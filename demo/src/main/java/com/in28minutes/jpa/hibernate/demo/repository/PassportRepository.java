package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PassportRepository {
    @Autowired
    EntityManager em;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Passport save(Passport passport) {
        if (passport.getId() == null) {
            em.persist(passport);
        } else {
            em.merge(passport);
        }
        return passport;
    }

    public Passport findById(Long id) {
        return em.find(Passport.class, id);
    }

    public void deleteById(Long id) {
        Passport passport = findById(id);
        em.remove(passport);
    }

    public void playWithEntityManager() {
        Passport passport1 = new Passport("11111111111111");
        em.persist(passport1);
        Passport passport2 = findById(2L);
        em.persist(passport2);
        passport2.setNumber("222222");
        em.flush();

        //em.detach(course2); will not allow changes in the specific course
        //m.clear(); //will not allow further changes in both courses

        passport1.setNumber("33333333");
       // course2.setName("Angular Js in 100 steps updated");

        //em.refresh(course1); //we will get the data from the database without changes
        em.flush();

    }
}
