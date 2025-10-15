package com.cg.stm.services.impl;

import com.cg.stm.entities.Task;
import com.cg.stm.mappers.TaskMapper;
import com.cg.stm.models.TaskDto;
import com.cg.stm.repositories.TaskRepository;
import com.cg.stm.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of the TaskService interface.
 * Handles business logic for managing tasks.
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    /**
     * Creates a new task.
     * @param taskDto the task data transfer object containing task details
     * @return the created task as a DTO
     */
    @Override
    public TaskDto create(TaskDto taskDto) {
        Task task = taskMapper.taskDtoToTask(taskDto);
        task.setCreatedAt(LocalDateTime.now());
        return taskMapper.taskToTaskDto(taskRepository.save(task));
    }

    /**
     * Finds a task by its ID.
     * @param taskId the ID of the task to retrieve
     * @return the found task as a DTO
     * @throws RuntimeException if the task does not exist
     */
    @Override
    public TaskDto findById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task with Id : " + taskId + " does not exist"));
        return taskMapper.taskToTaskDto(task);
    }

    /**
     * Retrieves all tasks.
     * @return a list of all tasks as DTOs
     */
    @Override
    public List<TaskDto> findAll() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDto> taskDtoList = tasks.stream()
                .map(taskMapper::taskToTaskDto)
                .toList();
        return taskDtoList;
    }

    /**
     * Updates an existing task.
     * @param taskId the ID of the task to update
     * @param taskDto the updated task data
     * @return the updated task as a DTO
     * @throws RuntimeException if the task does not exist
     */
    @Override
    public TaskDto update(Long taskId, TaskDto taskDto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task with Id : " + taskId + " does not exist"));
        task.setName(taskDto.getName());
        task.setStatus(taskDto.getStatus());
        task.setUpdatedAt(LocalDateTime.now());

        Task updatedTask = taskRepository.save(task);
        return taskMapper.taskToTaskDto(updatedTask);
    }

    /**
     * Deletes a task by its ID.
     * @param taskId the ID of the task to delete
     * @throws RuntimeException if the task does not exist
     */
    @Override
    public void delete(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Task with Id : " + taskId + " does not exist");
        }
        taskRepository.deleteById(taskId);
    }

    /**
     * Creates multiple tasks in a single operation.
     * @param taskDtoList List of tasks to be created.
     * @return List of created TaskDto objects.
     */
    @Override
    public List<TaskDto> createMultipleTasks(List<TaskDto> taskDtoList) {
        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks = taskDtoList.stream()
                .map(taskMapper::taskDtoToTask)
                .peek(task -> task.setCreatedAt(now))
                .toList();

        List<Task> savedTasks = taskRepository.saveAll(tasks);

        return savedTasks.stream()
                .map(taskMapper::taskToTaskDto)
                .toList();
    }
}
