package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class PartTimeEmployee extends Employee{
    private BigDecimal hourlyWage;

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

    public PartTimeEmployee() {

    }

    public PartTimeEmployee(BigDecimal hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
}
