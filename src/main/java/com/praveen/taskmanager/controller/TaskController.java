
package com.praveen.taskmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.praveen.taskmanager.dto.ApiResponse;
import com.praveen.taskmanager.entity.Task;
import com.praveen.taskmanager.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Task> createTask(@Valid @RequestBody Task task) {
        Task savedTask = taskService.createTask(task);

        return new ApiResponse<>(
                true, "Task created successfully", savedTask);
    }

    @GetMapping
    public ApiResponse<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();

        return new ApiResponse<>(
                true, "Tasks retrieved successfully", tasks);
    }

    @GetMapping("/{id}")
    public ApiResponse<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);

        return new ApiResponse<>(
                true, "Task retrieved successfully", task);
    }

    @PutMapping("/{id}")
    public ApiResponse<Task> updateTask(
            @Valid @PathVariable Long id,
            @RequestBody Task task) {

        Task updatedTask = taskService.updateTask(id, task);

        return new ApiResponse<>(
                true, "Task updated successfully", updatedTask);
    }


@DeleteMapping("/{id}")
public ApiResponse<Void> deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);

    return new ApiResponse<>(
            true,
            "Task deleted successfully",
            null
    );
}

@GetMapping("/health")
public String health() {
    return "Task Manager API is running in Docker!";
}

}
