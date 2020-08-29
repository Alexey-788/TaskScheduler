package com.alex.domain;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
public class Task {
    @Id
    @SequenceGenerator(name="user_gen", sequenceName="user_seq")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="user_gen")
    private long id;
    private String name;
    private String text;
    private Date deadline;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public Task(String name, String text, Date deadline, User user) {
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
