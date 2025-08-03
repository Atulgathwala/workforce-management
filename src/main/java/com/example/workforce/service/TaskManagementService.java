package com.example.workforce.service;

import com.example.workforce.common.model.Comment;
import com.example.workforce.common.model.TaskManagement;
import com.example.workforce.dto.*;

import java.util.List;

public interface TaskManagementService {
    List<TaskManagementDto> createTasks(TaskCreateRequest request);
    List<TaskManagementDto> updateTasks(UpdateTaskRequest request);
    String assignByReference(AssignByReferenceRequest request);
    List<TaskManagementDto> fetchTasksByDate(TaskFetchByDateRequest request);
    TaskManagementDto findTaskById(Long id);
    List<TaskManagement> getAllRawTasks();
    String updateTaskPriority(UpdatePriorityRequest request);
    List<TaskManagementDto> getTasksByPriority(String priority);
    String addCommentToTask(Long taskId, Comment comment);



}
