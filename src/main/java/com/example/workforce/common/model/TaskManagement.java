package com.example.workforce.common.model;

import com.example.workforce.common.model.enums.Priority;
import com.example.workforce.common.model.enums.Task;
import com.example.workforce.common.model.enums.TaskStatus;
import com.example.workforce.common.model.enums.ReferenceType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class TaskManagement {
    private Long id;
    private Long referenceId;
    private ReferenceType referenceType;
    private Task task;
    private String description;
    private TaskStatus status;
    private Long assigneeId;
    private Long taskDeadlineTime;
    private List<Comment> comments = new ArrayList<>();
    private List<String> activityLogs = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Priority priority;

}
