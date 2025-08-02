package com.example.workforce.controller;

import com.example.workforce.common.model.TaskManagement;
import com.example.workforce.dto.TaskManagementDto;
import com.example.workforce.dto.TaskCreateRequest;
import com.example.workforce.dto.UpdateTaskRequest;
import com.example.workforce.dto.AssignByReferenceRequest;
import com.example.workforce.dto.TaskFetchByDateRequest;
import com.example.workforce.common.model.response.Response;
import com.example.workforce.service.TaskManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-mgmt")
public class TaskController {

    private final TaskManagementService taskManagementService;

    public TaskController(TaskManagementService taskManagementService) {
        this.taskManagementService = taskManagementService;
    }

    @GetMapping("/{id}")
    public Response<TaskManagementDto> getTaskById(@PathVariable Long id) {
        return new Response<>(taskManagementService.findTaskById(id));
    }

    @PostMapping("/create")
    public Response<List<TaskManagementDto>> createTasks(@RequestBody TaskCreateRequest request) {
        return new Response<>(taskManagementService.createTasks(request));
    }

    @PostMapping("/update")
    public Response<List<TaskManagementDto>> updateTasks(@RequestBody UpdateTaskRequest request) {
        return new Response<>(taskManagementService.updateTasks(request));
    }

    @PostMapping("/assign-by-ref")
    public Response<String> assignByReference(@RequestBody AssignByReferenceRequest request) {
        return new Response<>(taskManagementService.assignByReference(request));
    }

    @PostMapping("/fetch-by-date/v2")
    public Response<List<TaskManagementDto>> fetchByDate(@RequestBody TaskFetchByDateRequest request) {
        return new Response<>(taskManagementService.fetchTasksByDate(request));
    }
}
