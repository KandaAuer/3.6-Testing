package com.example.testing.service;

import com.example.testing.model.Faculty;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacultyService {

    public List<Faculty> getAllFaculties() {
        return List.of(
                new Faculty(1L, "Gryffindor"),
                new Faculty(2L, "Slytherin"),
                new Faculty(3L, "Ravenclaw"),
                new Faculty(4L, "Hufflepuff"),
                new Faculty(5L, "Durmstrang")
        );
    }

    public Object getFacultyById(int i) {
    }

    public Object createFaculty(Faculty any) {
    }
}
