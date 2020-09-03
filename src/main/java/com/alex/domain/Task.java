package com.alex.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Task {
    @Id
    @SequenceGenerator(name="task_gen", sequenceName="task_seq")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="task_gen")
    private long id;
    private String name;
    private String text;
    private Calendar deadline;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public Task(String name, String text, Calendar deadline, User user) {
        this.name = name;
        this.text = text;
        this.deadline = deadline;
        this.user = user;
    }

    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", deadline=" + deadline +
                ", user=" + user +
                '}';
    }
}
