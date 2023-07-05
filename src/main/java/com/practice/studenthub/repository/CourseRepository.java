package com.practice.studenthub.repository;


import com.practice.studenthub.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
