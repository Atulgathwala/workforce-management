package com.example.workforce.service;

import com.example.workforce.common.model.TaskManagement;
import com.example.workforce.dto.TaskManagementDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final List<TaskManagement> tasks = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public void createTask(TaskManagementDto dto) {
        TaskManagement task = new TaskManagement();
        task.setId(idGenerator.incrementAndGet());
        task.setReferenceId(dto.getReferenceId());
        task.setReferenceType(dto.getReferenceType());
        task.setTask(dto.getTask());
        task.setAssigneeId(dto.getAssigneeId());
        task.setPriority(dto.getPriority());
        task.setStatus(dto.getStatus());
        task.setDescription(dto.getDescription());
        task.setTaskDeadlineTime(dto.getTaskDeadlineTime());

        tasks.add(task);
    }

    public List<TaskManagement> getAllTasks() {
        return tasks;
    }
}
