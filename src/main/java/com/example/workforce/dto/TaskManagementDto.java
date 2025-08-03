package com.example.workforce.dto;

import com.example.workforce.common.model.Comment;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.example.workforce.common.model.enums.ReferenceType;
import com.example.workforce.common.model.enums.Priority;
import com.example.workforce.common.model.enums.Task;
import com.example.workforce.common.model.enums.TaskStatus;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TaskManagementDto {
    private Long id;
    private Long referenceId;
    private ReferenceType referenceType;
    private Task task;
    private String description;
    private TaskStatus status;
    private Long assigneeId;
    private Long taskDeadlineTime;
    private Priority priority;
    private List<Comment> comments;
    private List<String> activityLogs;

}
