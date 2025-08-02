package com.example.workforce.service;

import com.example.workforce.dto.AssignByReferenceRequest;
import com.example.workforce.dto.TaskCreateRequest;
import com.example.workforce.dto.TaskFetchByDateRequest;
import com.example.workforce.dto.TaskManagementDto;
import com.example.workforce.dto.UpdateTaskRequest;

import java.util.List;

public interface TaskManagementService {
    List<TaskManagementDto> createTasks(TaskCreateRequest request);
    List<TaskManagementDto> updateTasks(UpdateTaskRequest request);
    String assignByReference(AssignByReferenceRequest request);
    List<TaskManagementDto> fetchTasksByDate(TaskFetchByDateRequest request);
    TaskManagementDto findTaskById(Long id);
}
