package com.alex.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class TaskEntity {
    @Id
    @SequenceGenerator(name="task_gen", sequenceName="task_seq")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="task_gen")
    private long id;
    /**
     * TaskPackage show in which package is TaskEntity. If the TaskPackage is null,
     * then this means that the task is at the root.
     */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="task_package_id")
    private PackageEntity taskPackage;
    private String name;
    private String text;
    private Calendar deadline;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserEntity user;

    public TaskEntity(PackageEntity taskPackage, String name, String text, Calendar deadline, UserEntity user) {
        this(name, text, deadline, user);
        this.taskPackage = taskPackage;
    }

    public TaskEntity(String name, String text, Calendar deadline, UserEntity user) {
        this.name = name;
        this.text = text;
        this.deadline = deadline;
        this.user = user;
    }

    public TaskEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PackageEntity getTaskPackage() {
        return taskPackage;
    }

    public void setTaskPackage(PackageEntity taskPackage) {
        this.taskPackage = taskPackage;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
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
