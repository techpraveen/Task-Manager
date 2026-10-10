//create the Service layer, which contains the business logic between the repository and the API controller.
//What have we implemented?
//Method	Purpose
//createTask()	Saves a new task
//getAllTasks()	Retrieves all tasks
//getTaskById()	Finds one task by ID
//updateTask()	Updates an existing task
//deleteTask()	Deletes a task
//@Service tells Spring to manage this class as a service bean. Constructor injection supplies the TaskRepository automatically.
package com.praveen.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.praveen.taskmanager.entity.Task;
import com.praveen.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found with ID: " + id));
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}
