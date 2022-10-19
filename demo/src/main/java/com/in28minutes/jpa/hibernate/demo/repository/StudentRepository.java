package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {
    @Autowired
    EntityManager em1;
    @Autowired
    private CourseRepository courseRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Student save(Student student) {
        if (student.getId() == null) {
            em1.persist(student);
        } else {
            em1.merge(student);
        }
        return student;
    }

    public Student findById(Long id) {
        return em1.find(Student.class, id);
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em1.remove(student);
    }

    /*public void playWithEntityManager() {
        Student student1 = new Student("Ranga");
        em.persist(student1);
        Student student2 = findById(2L);
        em.persist(student2);
        student2.setName("John");
        em.flush();

        //em.detach(course2); will not allow changes in the specific course
        //m.clear(); //will not allow further changes in both courses

        student1.setName("Entity manager updated");
        // course2.setName("Angular Js in 100 steps updated");

        //em.refresh(course1); //we will get the data from the database without changes
        em.flush();

    }*/

    public void saveStudentWithPassword() {
        Passport passport = new Passport(40005L, "55555555");
        em1.persist(passport);
        Student student = new Student(20008L, "Mike");
        em1.persist(student);
        student.setPassport(passport);

    }

    public void insertHardCodedStudentAndCourse() {
        Student student = new Student(40006L, "Jack");
        Course course = new Course(10006L, "Microservices in 100 Steps");
        Student student1 = new Student(40007L, "Jack");

        em1.persist(student);
        em1.persist(student1);
        em1.persist(course);

        student.addCourse(course);
        course.addStudent(student);
        student1.addCourse(courseRepository.findById(10002L));
        em1.persist(student);
        em1.persist(student1);
    }

    public void insertStudentAndCourse(Student student, Course course) {
        student.addCourse(course);
        course.addStudent(student);

        em1.persist(student);
        em1.persist(course);


    }
}
