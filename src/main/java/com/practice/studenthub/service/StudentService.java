package com.practice.studenthub.service;

import com.practice.studenthub.entity.Course;
import com.practice.studenthub.entity.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {
    Student getStudent(Long id);

    Student saveStudent(Student student);

    void deleteStudent(Long id);

    List<Student> getStudents();

    Set<Course> getEnrolledCourses(Long id);
}
