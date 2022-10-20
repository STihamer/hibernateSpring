package com.in28minutes.jpa.hibernate.demo;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.PartTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import com.in28minutes.jpa.hibernate.demo.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CourseRepository repository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //studentRepository.insertStudentAndCourse(new Student("Jack"), new Course("Microservices in 100 steps"));
        //studentRepository.insertHardCodedStudentAndCourse();
        /*List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(50004L, "5", "Great hands-on stuff."));
        reviews.add(new Review(50005L, "4", "Hatsoff."));*/

        //courseRepository.addReviewsForCourse(10003L, reviews);

       /* Course course1 = new Course(10001L, "Microservices in 1000 steps");
        Course course2 = new Course(10002L, "Bootstrap");
        Course course3 = new Course(10003L, "Javascript");
        repository.save(course1);
        repository.save(course2);
        repository.save(course3);*/
        //Course course = repository.findById(1L);
        //logger.info("Course 1 -{}", course);

        //repository.deleteById(1L);

        //repository.playWithEntityManager();

      /*  passportRepository.save(new Passport(40001L, "22222222"));
        passportRepository.save(new Passport(40002L, "333333333"));
        passportRepository.save(new Passport(40003L, "444444444"));
        Passport passport1 = passportRepository.findById(40001L);
        Passport passport2 = passportRepository.findById(40002L);
        Passport passport3 = passportRepository.findById(40003L);
        logger.info("Passport 40001 -{}", passport1);*/


       /* studentRepository.saveStudentWithPassword();
        studentRepository.save(new Student(20001L, "Ranga", passport1));
        studentRepository.save(new Student(20002L, "John", passport2));
        studentRepository.save(new Student(20003L, "Michael", passport3));
        Student student = studentRepository.findById(4L);
        logger.info("Student 4 -{}", student);
*/

     /*   reviewRepository.save(new Review(30001L, "2.66", "mama", course1));
        reviewRepository.save(new Review(30002L, "2.66", "papa",course1));
        reviewRepository.save(new Review(30003L, "2.66", "agi", course2));
        Review review = reviewRepository.findById(12L);
        logger.info("Review 12 -{}", review);*/

        /*employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
        employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("1000000")));

        logger.info("employees -> {}", employeeRepository.retrieveAllEmployees());*/


        /*employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
        employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("1000000")));

        logger.info("Full time employees -> {}", employeeRepository.retrieveAllFullTimeEmployees());
        logger.info("Part time employees -> {}", employeeRepository.retrieveAllPartTimeEmployees());*/

    }
}

    /*ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(DemoApplication.class,
    args);
    CourseRepository courseRepository = configurableApplicationContext.getBean(CourseRepository.class);
    Course myCourse = new Course("Tihi");
        courseRepository.save(myCourse);*/