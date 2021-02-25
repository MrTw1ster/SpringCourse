package ru.gb.spring.course;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.http.HttpRequest;

@Component
@Scope("prototype")
public class DemoBean {


    private String title;

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    @PostConstruct
    public void init() {
        title = "abcd1";
    }
}
