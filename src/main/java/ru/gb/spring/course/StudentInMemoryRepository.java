package ru.gb.spring.course;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class StudentInMemoryRepository implements StudentRepository {
    private List<Student> students;

    @Override
    public List<Student> getStudents() {

        return Collections.unmodifiableList(students);
    }


    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        students.add(new Student(1L, "Bob", 100));
        students.add(new Student(2L, "John", 100));
    }

    public void add(Student student) {
        if (student.getScore() < 0 || student.getScore() > 100)
            throw new IllegalArgumentException("Student's score must be in interval [0, 000100]");

        students.add(student);
    }
}
