package com.practice.studenthub;

import com.practice.studenthub.entity.Course;
import com.practice.studenthub.entity.Student;
import com.practice.studenthub.repository.CourseRepository;
import com.practice.studenthub.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;

@AllArgsConstructor
@SpringBootApplication
public class StudentHubApplication implements CommandLineRunner {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(StudentHubApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
		Student[] students = {
				new Student("Emily Johnson", LocalDate.parse("2001-05-15")),
				new Student("David Martinez", LocalDate.parse("1999-11-03")),
				new Student("Samantha Thompson", LocalDate.parse("2002-08-27")),
				new Student("Benjamin Lee", LocalDate.parse("2000-02-19"))
		};

		Arrays.stream(students).forEach(studentRepository::save);

		Course[] courses = {
				new Course("Mathematics", "MTH101",
						"In this class, you will explore various mathematical concepts and problem-solving techniques."),
				new Course("Physics", "PHY201",
						"In this class, you will learn the fundamental principles of physics and their applications."),
				new Course("Biology", "BIO301",
						"In this class, you will study the living organisms, their structure, functions, and interactions."),
				new Course("History", "HIS401",
						"In this class, you will learn about significant events and developments throughout history."),
				new Course("English Literature", "ENG501",
						"In this class, you will analyze and interpret various literary works and enhance your reading skills."),
				new Course("Computer Science", "CSC601",
						"In this class, you will learn programming concepts and develop problem-solving skills using computers.")
		};

		Arrays.stream(courses).forEach(courseRepository::save);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
