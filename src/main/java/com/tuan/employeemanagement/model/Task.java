package com.tuan.employeemanagement.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskId")
    private long id;

    @Column(name = "taskName")
    private String taskName;
    @Column(name = "decription")
    private String decription;

    @ManyToOne
    @JoinColumn(name = "id")
    private Employees assignee;
    @Column(name = "deadline")
    private String deadline;
    @Column(name = "status")
    private String status;

    public Task() {
    }

    public Task(long id, String taskName, String decription, Employees assignee, String deadline, String status) {
        this.id = id;
        this.taskName = taskName;
        this.decription = decription;
        this.assignee = assignee;
        this.deadline = deadline;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Employees getAssignee() {
        return assignee;
    }

    public void setAssignee(Employees assignee) {
        this.assignee = assignee;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
