package com.alex;

import com.alex.domain.Role;
import com.alex.domain.Status;
import com.alex.domain.Task;
import com.alex.domain.User;
import com.alex.repository.TaskRepo;
import com.alex.repository.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.GregorianCalendar;

@SpringBootApplication
public class TaskSchedulerApp {

    private UserRepo userRepo;
    private TaskRepo taskRepo;

    public TaskSchedulerApp(UserRepo userRepo, TaskRepo taskRepo) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TaskSchedulerApp.class, args);
        TaskSchedulerApp app = context.getBean(TaskSchedulerApp.class);
    }
}