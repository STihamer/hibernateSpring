package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(path = "courses")
public  interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
    List<Course>findByName(String name);
    List<Course>findByNameOrderByIdDesc(String name);
    List<Course>countByName(String name);
    List<Course>deleteByName(String name);

    @Query("Select c from Course c where name like '%1%'")
    List<Course>coursesWithStepsInName();

    @Query(value = "Select * from Course c where name like '%1%'", nativeQuery = true) //native query
    List<Course>coursesWithStepsInNameUsingNativeQuery();

    @Query(name = "query_get_100") //named query
    List<Course> coursesWithStepsInNameUsingNamedQuery();
}
