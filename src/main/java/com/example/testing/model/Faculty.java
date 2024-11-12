package com.example.testing.model;

public class Faculty {
    private Long id;
    private String name;

    // Конструктор
    public Faculty(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Faculty() {

    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
