package com.alex.dto;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestParam;

public class TaskDto {
    private String text;
    private String name;
    private int days;
    private int hours;
    private int minutes;

    public TaskDto(String text, String name, int days, int hours, int minutes) {
        this.text = text;
        this.name = name;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
    }

    public TaskDto(String text, String name) {
        this.text = text;
        this.name = name;
    }

    public TaskDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String taskName) {
        this.name = taskName;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
