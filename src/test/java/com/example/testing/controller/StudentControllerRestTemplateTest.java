package com.example.testing.controller;

import com.example.testing.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    public StudentControllerRestTemplateTest(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Test
    public void testGetStudentCount() {
        ResponseEntity<Integer> response = restTemplate.getForEntity("/students/count", Integer.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isGreaterThan(0);
    }

    @Test
    public void testGetAverageStudentAge() {
        ResponseEntity<Double> response = restTemplate.getForEntity("/students/average-age", Double.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isGreaterThan(0);
    }

    @Test
    public void testGetLastFiveStudents() {
        ResponseEntity<List> response = restTemplate.getForEntity("/students/last-five", List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isLessThanOrEqualTo(5);
    }

    @Test
    public void testGetStudentById() {
        ResponseEntity<Student> response = restTemplate.getForEntity("/students/1", Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1);
    }

    @Test
    public void testGetNonExistingStudentById() {
        ResponseEntity<Student> response = restTemplate.getForEntity("/students/99999", Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testCreateStudent() {
        Student newStudent = new Student();
        newStudent.setName("Новый студент");
        newStudent.setAge(20);
        ResponseEntity<Student> response = restTemplate.postForEntity("/students", newStudent, Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Новый студент");
    }
}
