package com.example.workforce.common.model;

import com.example.workforce.common.model.enums.Priority;
import com.example.workforce.common.model.enums.Task;
import com.example.workforce.common.model.enums.TaskStatus;
import com.example.workforce.common.model.enums.ReferenceType;
import lombok.Data;

@Data
public class TaskManagement {
    private Long id;
    private Long referenceId;
    private ReferenceType referenceType;
    private Task task;
    private String description;
    private TaskStatus status;
    private Long assigneeId; // Simplified from Entity for this assignment
    private Long taskDeadlineTime;
    private Priority priority;
}
