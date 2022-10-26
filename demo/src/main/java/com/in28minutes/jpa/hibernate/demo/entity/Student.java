package com.in28minutes.jpa.hibernate.demo.entity;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch=FetchType.LAZY)
    private Passport passport;

    @ManyToMany
    @JoinTable(name = "STUDENT_COURSE",
    joinColumns = @JoinColumn(name = "STUDENT_ID"),
    inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    //Join column name - STUDENT_ID
    //inverse join column -COURSE_ID
    private List<Course> courses = new ArrayList<>();


    public void setId(Long id) {
        this.id = id;
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Student(Long id, String name, Passport passport) {
        this.id = id;
        this.name = name;
        this.passport = passport;
    }

    public Student() {

    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passport=" + passport +
                '}';
    }
}
