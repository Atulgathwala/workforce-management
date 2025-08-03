package com.example.workforce.dto;

public class UpdatePriorityRequest {
    private Long taskId;
    private String priority; // HIGH, MEDIUM, LOW

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
