package com.practice.studenthub.web;

import com.practice.studenthub.entity.Course;
import com.practice.studenthub.entity.Student;
import com.practice.studenthub.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody Course course) {
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }

    @PutMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<Course> enrollStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        return new ResponseEntity<>(courseService.addStudentToCourse(studentId, courseId), HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<Student>> getEnrolledStudents(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getEnrolledStudents(id), HttpStatus.OK);
    }
}
