package com.alex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TaskSchedulerApp {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TaskSchedulerApp.class, args);

    }
}
