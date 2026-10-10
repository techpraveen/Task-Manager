
package com.praveen.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.praveen.taskmanager.entity.Task;
import com.praveen.taskmanager.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTaskShouldSaveTask() {
        Task task = new Task();
        task.setTitle("Learn Docker");

        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);

        assertEquals("Learn Docker", result.getTitle());
        verify(taskRepository).save(task);
    }

@Test
void getAllTasksShouldReturnTasks() {
    Task task = new Task();
    task.setTitle("Learn Docker");

    when(taskRepository.findAll())
            .thenReturn(java.util.List.of(task));

    java.util.List<Task> result = taskService.getAllTasks();

    assertEquals(1, result.size());
    assertEquals("Learn Docker", result.get(0).getTitle());
    verify(taskRepository).findAll();
}

@Test
void getTaskByIdShouldReturnTask() {
    Task task = new Task();
    task.setTitle("Learn Kubernetes");

    when(taskRepository.findById(1L))
            .thenReturn(java.util.Optional.of(task));

    Task result = taskService.getTaskById(1L);

    assertEquals("Learn Kubernetes", result.getTitle());
    verify(taskRepository).findById(1L);
}

}
