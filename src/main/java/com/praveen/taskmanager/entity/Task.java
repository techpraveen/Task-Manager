// Understand what you created
//Component	Purpose
//@Entity	Maps the Java class to a database table
//@Id	Marks the primary key
//@GeneratedValue	Lets MySQL generate the ID
//title	Task name
//status	Defaults to PENDING
//dueDate	Task deadline
//Because you configured spring.jpa.hibernate.ddl-auto=update,
//Hibernate should create the task table automatically when the application starts,
//provided entity scanning and database permissions are configured correctly.
package com.praveen.taskmanager.entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Task title is required")
    @Size(max = 100, message = "Task title cannot exceed 100 characters")
    private String title;
    private String description;

    private String status = "PENDING";

    private LocalDate dueDate;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
