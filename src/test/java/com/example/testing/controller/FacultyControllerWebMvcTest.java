package com.example.testing.controller;

import com.example.testing.model.Faculty;
import com.example.testing.service.FacultyService;
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

@WebMvcTest(FacultyController.class)
public class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    public FacultyControllerWebMvcTest(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Test
    public void testGetAllFaculties() throws Exception {
        Faculty faculty1 = new Faculty(1L, "Gryffindor");
        Faculty faculty2 = new Faculty(2L, "Slytherin");

        when(facultyService.getAllFaculties()).thenReturn(Arrays.asList(faculty1, faculty2));

        mockMvc.perform(get("/faculties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Gryffindor")))
                .andExpect(jsonPath("$[1].name", is("Slytherin")));
    }

    @Test
    public void testGetFacultyById() throws Exception {
        Faculty faculty = new Faculty(1L, "Gryffindor");

        when(facultyService.getFacultyById(1)).thenReturn(faculty);

        mockMvc.perform(get("/faculties/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Gryffindor")));
    }

    @Test
    public void testCreateFaculty() throws Exception {
        Faculty newFaculty = new Faculty(null, "Hufflepuff");
        Faculty savedFaculty = new Faculty(3L, "Hufflepuff");

        when(facultyService.createFaculty(Mockito.any(Faculty.class))).thenReturn(savedFaculty);

        mockMvc.perform(post("/faculties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Hufflepuff\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Hufflepuff")));
    }
}
