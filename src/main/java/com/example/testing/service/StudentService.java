package com.example.testing.service;

import com.example.testing.model.Student;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    public int getStudentCount() {
        return 100; // Пример возвращаемого значения
    }

    public double getAverageStudentAge() {
        return 16.5; // Пример возвращаемого значения
    }

    public List<Student> getLastFiveStudents() {
        return List.of(
                new Student(1L, "Harry", 15),
                new Student(2L, "Hermione", 16),
                new Student(3L, "Ron", 15),
                new Student(4L, "Luna", 16),
                new Student(5L, "Neville", 17)
        );
    }
}
