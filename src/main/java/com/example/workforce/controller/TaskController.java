package com.example.workforce.controller;

import com.example.workforce.common.model.Comment;
import com.example.workforce.dto.*;
import com.example.workforce.service.TaskService;
import com.example.workforce.common.model.TaskManagement;
import com.example.workforce.common.model.response.Response;
import com.example.workforce.service.TaskManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-mgmt")
public class TaskController {

    private final TaskManagementService taskManagementService;
    private final TaskService taskService; // ✅ Add this

    // ✅ Inject both services in the constructor
    public TaskController(TaskManagementService taskManagementService, TaskService taskService) {
        this.taskManagementService = taskManagementService;
        this.taskService = taskService;
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

    @GetMapping
    public List<TaskManagement> getAllTasks() {
        return taskManagementService.getAllRawTasks();  // ✅ Now works
    }

    @PostMapping("/update-priority")
    public Response<String> updatePriority(@RequestBody UpdatePriorityRequest request) {
        return new Response<>(taskManagementService.updateTaskPriority(request));
    }

    @GetMapping("/priority/{priority}")
    public Response<List<TaskManagementDto>> getByPriority(@PathVariable String priority) {
        return new Response<>(taskManagementService.getTasksByPriority(priority));
    }


    @PostMapping("/{id}/comment")
    public Response<String> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        return new Response<>(taskManagementService.addCommentToTask(id, comment));
    }



}
