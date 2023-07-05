package com.practice.studenthub.service;

import com.practice.studenthub.entity.Course;
import com.practice.studenthub.entity.Student;
import com.practice.studenthub.exception.EntityNotFoundException;
import com.practice.studenthub.repository.CourseRepository;
import com.practice.studenthub.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    static Course unwrapCourse(Optional<Course> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Course.class);
    }

    @Override
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return unwrapCourse(course, id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course addStudentToCourse(Long studentId, Long courseId) {
        Course course = getCourse(courseId);
        Optional<Student> student = studentRepository.findById(studentId);
        Student unwrappedStudent = StudentServiceImpl.unwrapStudent(student, studentId);
        course.getStudents().add(unwrappedStudent);
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Set<Student> getEnrolledStudents(Long id) {
        Course course = getCourse(id);
        return course.getStudents();
    }
}
