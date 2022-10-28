package com.in28minutes.jpa.hibernate.demo.entity;


import javax.persistence.*;


@Entity
public class Review {

    @Id
    //@GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReviewRating rating;
    private String description;

    @ManyToOne //eager fetch by default
    private Course course;

    public ReviewRating getRating() {
        return rating;
    }

    public void setRating(ReviewRating rating) {
        this.rating = rating;
    }

    public Review(Long id, ReviewRating rating, String description) {
        this.id = id;
        this.rating = rating;
        this.description = description;
    }

    public Review(ReviewRating rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Review() {

    }

    public Long getId() {
        return id;
    }

    public Review(Long id, ReviewRating rating, String description, Course course) {
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
