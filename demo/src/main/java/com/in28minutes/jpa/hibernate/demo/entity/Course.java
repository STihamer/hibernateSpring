package com.in28minutes.jpa.hibernate.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "query_get_all_courses", query = "Select c from Course c"),
        @NamedQuery(name = "query_get_100", query = "select c from Course c where name  like '%Jpa%'"),
        @NamedQuery(name = "query_get_all_courses_join_fetch", query = "Select c from Course c JOIN  fetch c.students")
})
@Cacheable
@SQLDelete(sql = "update course set is_deleted=true where id = ?")  //this is not a jpa annotation this is a hibernate
// annotation
@Where(clause = "is_deleted = false") //I would only want to retrieve those rows for which 'is_deleted' is 'false'
public class Course {
    private static Logger LOGGER = LoggerFactory.getLogger(Course.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "course") //lazy fetch by default
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();
    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;


    private boolean isDeleted;


    @PreRemove //whenever a row is of a specific entity is deleted there is a method that gets fired
    private void preRemove(){
        LOGGER.info("Setting isDeleted to True");
        this.isDeleted = true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course() {

    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
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

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", is_deleted='" + isDeleted + '\'' +
                '}';
    }
}
