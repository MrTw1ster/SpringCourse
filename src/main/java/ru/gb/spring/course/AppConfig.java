package ru.gb.spring.course;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"ru.gb.spring.course"})
public class AppConfig {

    @Bean
    public Box box(StudentService studentService) {
        Box box = new Box();
        box.setColor("Red");
        box.setSize(10);
        box.setStudentService(studentService);
        return box;
    }

}
