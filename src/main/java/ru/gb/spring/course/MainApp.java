package ru.gb.spring.course;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {



    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        /*DemoBean demoBean1 = context.getBean("demoBean", DemoBean.class);
        System.out.println(demoBean1.getTitle());



        DemoBean demoBean2 = context.getBean("demoBean", DemoBean.class);
        System.out.println(demoBean2.getTitle());

        System.out.println(demoBean1.hashCode());
        System.out.println(demoBean2.hashCode());
        context.close();
    }*/
        Box box = context.getBean("box", Box.class);
        System.out.println(box.doSmthWithStudentService());

        context.close();
}}
