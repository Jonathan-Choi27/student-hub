package com.practice.studenthub;

import com.practice.studenthub.entity.Course;
import com.practice.studenthub.entity.Student;
import com.practice.studenthub.repository.StudentRepository;
import com.practice.studenthub.service.StudentService;
import com.practice.studenthub.service.StudentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Testing one service for practice, testing other services should be similar
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    private StudentRepository studentRepository;
    private StudentService studentService;

    @Before
    public void setUp() {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);
    }

    @Test
    public void getStudentsFromRepositoryTest() {
        List<Student> students = Arrays.asList(
                new Student("Emily Johnson", LocalDate.parse("2001-05-15")),
                new Student("David Martinez", LocalDate.parse("1999-11-03"))
        );

        when(studentService.getStudents()).thenReturn(students);
        List<Student> result = studentService.getStudents();
        assertEquals("Emily Johnson", result.get(0).getName());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void getStudentByIdTest() {
        Long studentId = 1L;
        Student student = new Student("Emily Johnson", LocalDate.parse("2001-05-15"));

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        Student result = studentService.getStudent(studentId);

        assertEquals(student.getName(), result.getName());
        assertEquals(student.getBirthDate(), result.getBirthDate());

        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    public void saveStudentTest() {
        Student studentToSave = new Student("Emily Johnson");
        Student savedStudent = new Student("Emily Johnson");
        savedStudent.setId(1L);

        when(studentRepository.save(studentToSave)).thenReturn(savedStudent);

        Student result = studentService.saveStudent(studentToSave);

        assertEquals(savedStudent.getId(), result.getId());
        assertEquals(savedStudent.getName(), result.getName());

        verify(studentRepository, times(1)).save(studentToSave);
    }

    @Test
    public void deleteStudentTest() {
        Long studentId = 1L;
        studentService.deleteStudent(studentId);
        verify(studentRepository, times(1)).deleteById(studentId);
    }

    @Test
    public void getEnrolledCoursesTest() {
        Long studentId = 1L;
        Set<Course> enrolledCourses = new HashSet<>();
        enrolledCourses.add(new Course("Maths", "MATH", "Mathematics Course"));
        enrolledCourses.add(new Course("History", "HIS", "History Course"));

        Student student = new Student("Emily Johnson");
        student.setId(studentId);
        student.setCourses(enrolledCourses);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        Set<Course> result = studentService.getEnrolledCourses(studentId);

        assertEquals(enrolledCourses.size(), result.size());
        assertEquals(enrolledCourses, result);

        verify(studentRepository, times(1)).findById(studentId);
    }
}





