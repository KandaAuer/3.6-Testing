package com.example.testing.controller;

import com.example.testing.model.Faculty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    public FacultyControllerRestTemplateTest(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Test
    public void testGetAllFaculties() {
        ResponseEntity<List> response = restTemplate.getForEntity("/faculties", List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isGreaterThan(0);
    }

    @Test
    public void testGetFacultyById() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculties/1", Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1);
    }

    @Test
    public void testGetNonExistingFacultyById() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculties/99999", Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testCreateFaculty() {
        Faculty newFaculty = new Faculty();
        newFaculty.setName("Новый факультет");
        ResponseEntity<Faculty> response = restTemplate.postForEntity("/faculties", newFaculty, Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Новый факультет");
    }
}
