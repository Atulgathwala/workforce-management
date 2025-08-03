package com.example.workforce.service.impl;

import com.example.workforce.common.exception.ResourceNotFoundException;
import com.example.workforce.common.model.Comment;
import com.example.workforce.common.model.enums.Priority;
import com.example.workforce.common.model.enums.TaskPriority;
import com.example.workforce.dto.*;
import com.example.workforce.mapper.ITaskManagementMapper;
import com.example.workforce.common.model.TaskManagement;
import com.example.workforce.common.model.enums.Task;
import com.example.workforce.common.model.enums.TaskStatus;
import com.example.workforce.repository.TaskRepository;
import com.example.workforce.service.TaskManagementService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskManagementServiceImpl implements TaskManagementService {

    private final TaskRepository taskRepository;
    private final ITaskManagementMapper taskMapper;

    public TaskManagementServiceImpl(TaskRepository taskRepository, ITaskManagementMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskManagementDto findTaskById(Long id) {
        TaskManagement task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        return taskMapper.modelToDto(task);
    }

    @Override
    public List<TaskManagement> getAllRawTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<TaskManagementDto> createTasks(TaskCreateRequest createRequest) {
        List<TaskManagement> createdTasks = new ArrayList<>();
        for (TaskCreateRequest.RequestItem item : createRequest.getRequests()) {
            TaskManagement newTask = new TaskManagement();
            newTask.setReferenceId(item.getReferenceId());
            newTask.setReferenceType(item.getReferenceType());
            newTask.setTask(item.getTask());
            newTask.setAssigneeId(item.getAssigneeId());
            newTask.setPriority(item.getPriority());
            newTask.setTaskDeadlineTime(item.getTaskDeadlineTime());
            newTask.setStatus(TaskStatus.ASSIGNED);
            newTask.setDescription("New task created.");
            createdTasks.add(taskRepository.save(newTask));
        }
        return taskMapper.modelListToDtoList(createdTasks);
    }

    @Override
    public List<TaskManagementDto> updateTasks(UpdateTaskRequest updateRequest) {
        List<TaskManagement> updatedTasks = new ArrayList<>();
        for (UpdateTaskRequest.RequestItem item : updateRequest.getRequests()) {
            TaskManagement task = taskRepository.findById(item.getTaskId())
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + item.getTaskId()));

            if (item.getTaskStatus() != null) {
                task.setStatus(item.getTaskStatus());
            }
            if (item.getDescription() != null) {
                task.setDescription(item.getDescription());
            }
            updatedTasks.add(taskRepository.save(task));
        }
        return taskMapper.modelListToDtoList(updatedTasks);
    }

    @Override
    public String assignByReference(AssignByReferenceRequest request) {
        List<Task> applicableTasks = Task.getTasksByReferenceType(request.getReferenceType());
        List<TaskManagement> existingTasks = taskRepository.findByReferenceIdAndReferenceType(
                request.getReferenceId(), request.getReferenceType());

        for (Task taskType : applicableTasks) {
            List<TaskManagement> tasksOfType = existingTasks.stream()
                    .filter(t -> t.getTask() == taskType && t.getStatus() != TaskStatus.COMPLETED)
                    .collect(Collectors.toList());

            // ✅ Cancel existing tasks
            for (TaskManagement taskToCancel : tasksOfType) {
                taskToCancel.setStatus(TaskStatus.CANCELLED);
                taskRepository.save(taskToCancel);
            }

            // ✅ Create a new task with updated assignee
            TaskManagement newTask = new TaskManagement();
            newTask.setReferenceId(request.getReferenceId());
            newTask.setReferenceType(request.getReferenceType());
            newTask.setTask(taskType);
            newTask.setAssigneeId(request.getAssigneeId());
            newTask.setStatus(TaskStatus.ASSIGNED);
            newTask.setDescription("Reassigned task");
            taskRepository.save(newTask);
        }

        return "Tasks reassigned successfully for reference " + request.getReferenceId();
    }

    @Override
    public List<TaskManagementDto> fetchTasksByDate(TaskFetchByDateRequest request) {
        List<TaskManagement> tasks = taskRepository.findByAssigneeIdIn(request.getAssigneeIds());

        List<TaskManagement> filteredTasks = tasks.stream()
                .filter(task ->
                        task.getStatus() != TaskStatus.CANCELLED &&
                                (
                                        (task.getTaskDeadlineTime() >= request.getStartDate() &&
                                                task.getTaskDeadlineTime() <= request.getEndDate()) ||

                                                (task.getTaskDeadlineTime() < request.getStartDate() &&
                                                        task.getStatus() != TaskStatus.COMPLETED)
                                )
                )
                .collect(Collectors.toList());

        return taskMapper.modelListToDtoList(filteredTasks);
    }


    @Override
    public String updateTaskPriority(UpdatePriorityRequest request) {
        TaskManagement task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + request.getTaskId()));

        task.setPriority(Priority.valueOf(request.getPriority()));
// Assuming TaskPriority is your enum
        taskRepository.save(task);
        return "Priority updated successfully.";
    }


    @Override
    public List<TaskManagementDto> getTasksByPriority(String priority) {
        Priority taskPriority = Priority.valueOf(priority.toUpperCase());
        List<TaskManagement> filtered = taskRepository.findByPriority(taskPriority);


        return taskMapper.modelListToDtoList(filtered);
    }


    @Override
    public String addCommentToTask(Long taskId, Comment comment) {
        TaskManagement task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        task.getComments().add(comment);
        task.getActivityLogs().add("Comment added by " + comment.getAuthor() + " at " + comment.getTimestamp());

        taskRepository.save(task);
        return "Comment added successfully.";
    }




}
