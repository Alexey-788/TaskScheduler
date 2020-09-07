package com.alex.controller;

import com.alex.domain.Task;
import com.alex.repository.TaskRepo;
import com.alex.repository.UserRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.temporal.ChronoUnit;
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
        com.alex.domain.User currentUser = getDomainUser(user);
        model.addAttribute("username", currentUser.getName());
        model.addAttribute("tasks", currentUser.getTasks());
        //passing the current date for comparison with the deadline
        model.addAttribute("currentDate", new GregorianCalendar());
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
                          @RequestParam int hours,
                          @RequestParam int minutes) {
        taskRepo.save(new Task(taskName, taskText, new GregorianCalendar() {{
            add(HOUR, hours);
            add(DATE, days);
            add(MINUTE, minutes);
        }}, getDomainUser(user)));
        return "redirect:/task/list";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, @AuthenticationPrincipal User user, Model model) {
        Task currentTask = taskRepo.findById(id).orElse(null);
        com.alex.domain.User currentUser = getDomainUser(user);

        if (currentTask == null) {
            model.addAttribute("error", "Task with id " + id + " doesn't exist");
            return "task/error";
        } else if (currentUser.getTasks().stream().noneMatch(x -> x.equals(currentTask))) {
            model.addAttribute("error", "Task with id " + id + " belongs to another user");
            return "task/error";
        }

        //getting difference between now and deadline in days
        long daysLeft = ChronoUnit.DAYS.between(new GregorianCalendar().toInstant(),currentTask.getDeadline().toInstant());
        //getting difference between now and deadline in hours
        long hoursLeft = ChronoUnit.HOURS.between(new GregorianCalendar().toInstant(),currentTask.getDeadline().toInstant());
        //getting difference between now and deadline in hours
        long minutesLeft = ChronoUnit.MINUTES.between(new GregorianCalendar().toInstant(),currentTask.getDeadline().toInstant());
        System.out.println("Left " + daysLeft + " days, " + hoursLeft%24 + " hours " + minutesLeft%60 + "minutes");

        System.out.println(daysLeft);
        model.addAttribute("daysLeft", daysLeft);
        model.addAttribute("hoursLeft", hoursLeft);
        model.addAttribute("minutesLeft", minutesLeft);

        model.addAttribute("currentTask", currentTask);

        //passing the current date for comparison with the deadline
        model.addAttribute("currentDate", new GregorianCalendar());
        return "task/details";
    }

    @PostMapping("delete")
    public String delete(@RequestParam long taskId) {
        taskRepo.deleteById(taskId);
        return "redirect:/task/list";
    }

    @GetMapping("/edit")
    public String edit(@AuthenticationPrincipal User user, @RequestParam long taskId, Model model) {
        Task currentTask = taskRepo.findById(taskId).orElse(null);
        com.alex.domain.User currentUser = getDomainUser(user);

        if (currentTask == null) {
            model.addAttribute("error", "Task with id " + taskId + " doesn't exist");
            return "task/error";
        } else if (currentUser.getTasks().stream().noneMatch(x -> x.equals(currentTask))) {
            model.addAttribute("error", "Task with id " + taskId + " belongs to another user");
            return "task/error";
        }

        model.addAttribute("task", currentTask);
        return "task/edit";
    }

    @PostMapping("edit")
    public String edit(@RequestParam String taskName, @RequestParam String taskText, @RequestParam long taskId) {
        Task currentTask = taskRepo.findById(taskId).orElseThrow(
                () -> new EntityNotFoundException("Editing not available: Entity with id " + taskId + "not found")
        );

        currentTask.setName(taskName);
        currentTask.setText(taskText);

        taskRepo.save(currentTask);
        return "redirect:/task/list";
    }

    private com.alex.domain.User getDomainUser(User user) {
        return userRepo.findByLogin(user.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("Username " + user.getUsername() + " not found")
        );
    }
}
