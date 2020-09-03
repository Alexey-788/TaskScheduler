package com.alex.controller;

import com.alex.domain.Task;
import com.alex.repository.TaskRepo;
import com.alex.repository.UserRepo;
import com.alex.security.SecurityUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Controller for operation on tasks
 */
@Controller
@RequestMapping("task")
public class TaskController {

    private final UserRepo userRepo;
    private final TaskRepo taskRepo;

    public TaskController(UserRepo userRepo, TaskRepo taskRepo) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
    }

    @GetMapping("/list")
    public String taskList(@AuthenticationPrincipal User user, Model model) {
        com.alex.domain.User currentUser = userRepo.findByLogin(user.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("Getting task list don't available: username " + user.getUsername() + " not found")
        );
        model.addAttribute("tasks", currentUser.getTasks());
        model.addAttribute("username", currentUser.getName());
        return "task/list";
    }

    @GetMapping("/add")
    public String addTask(Model model) {
        return "task/add";
    }

    @PostMapping("/add")
    public String addTask(@AuthenticationPrincipal User user,
                          @RequestParam String taskName,
                          @RequestParam String taskText,
                          @RequestParam int days,
                          @RequestParam int hours) {
        taskRepo.save(new Task(taskName, taskText, new GregorianCalendar() {{
            add(HOUR, hours);
            add(DATE, days);
        }}, userRepo.findByLogin(user.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("Adding don't available UserName " + user.getUsername() + " not found")
        )));
        return "redirect:/task/list";
    }
}
