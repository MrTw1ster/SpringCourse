package ru.gb.spring.course;

public class Box {
    private StudentService studentService;
    private String color;
    private int size;

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String red) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int doSmthWithStudentService() {
        return studentService.calculateAverageScore();
    }
}