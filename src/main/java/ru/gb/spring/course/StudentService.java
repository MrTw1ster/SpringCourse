package ru.gb.spring.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    /*private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }*/

    public int calculateAverageScore() {
        List<Student> students = studentRepository.getStudents();
        if (students.size() == 0) {
            return 0;
        }

        int avg=0;
        for (Student s: students) {
            avg+=s.getScore();
        }
        avg/=students.size();
        return avg;
    }
}
