package com.practice.studenthub.repository;

import com.practice.studenthub.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
