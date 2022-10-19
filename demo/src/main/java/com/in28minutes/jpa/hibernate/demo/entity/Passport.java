package com.in28minutes.jpa.hibernate.demo.entity;


import javax.persistence.*;

@Entity
@Table(name="passport")
public class Passport {

    @Id
    //@GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String number;

    @OneToOne(fetch=FetchType.LAZY, mappedBy = "passport")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Passport(String number) {
        this.number = number;
    }

    public Passport(Long id, String number) {
        this.id = id;
        this.number = number;
    }

    public Passport() {

    }

    public Long getId() {
        return id;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String name) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }

}
