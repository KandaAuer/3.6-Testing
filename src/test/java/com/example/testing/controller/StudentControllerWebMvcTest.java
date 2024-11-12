package com.example.testing.controller;

import com.example.testing.model.Student;
import com.example.testing.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
        import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void testGetStudentCount() throws Exception {
        when(studentService.getStudentCount()).thenReturn(100);

        mockMvc.perform(get("/students/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("100"));
    }

    @Test
    public void testGetAverageStudentAge() throws Exception {
        when(studentService.getAverageAge()).thenReturn(20.5);

        mockMvc.perform(get("/students/average-age"))
                .andExpect(status().isOk())
                .andExpect(content().string("20.5"));
    }

    @Test
    public void testGetLastFiveStudents() throws Exception {
        Student student1 = new Student(1L, "Harry", 18);
        Student student2 = new Student(2L, "Hermione", 19);

        when(studentService.getLastFiveStudents()).thenReturn(Arrays.asList(student1, student2));

        mockMvc.perform(get("/students/last-five"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Harry")))
                .andExpect(jsonPath("$[1].name", is("Hermione")));
    }
}
