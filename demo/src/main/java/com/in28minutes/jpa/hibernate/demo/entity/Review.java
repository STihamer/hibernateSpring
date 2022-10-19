package com.in28minutes.jpa.hibernate.demo.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Review {

    @Id
    //@GeneratedValue
    private Long id;

    private String rating;
    private String description;

    @ManyToOne
    private Course course;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Review(Long id, String rating, String description) {
        this.id = id;
        this.rating = rating;
        this.description = description;
    }

    public Review(String rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Review() {

    }

    public Long getId() {
        return id;
    }

    public Review(Long id, String rating, String description, Course course) {
        this.id = id;
        this.rating = rating;
        this.description = description;
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
