package com.webapp.effectiveness.functionalities.task;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String taskName;

    @CreationTimestamp
    private LocalDateTime creationDate;

    public Task(String taskName, LocalDateTime creationDate) {
        this.taskName = taskName;
        this.creationDate = creationDate;
    }
}
