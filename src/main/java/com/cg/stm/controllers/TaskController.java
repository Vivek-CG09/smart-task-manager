package com.cg.stm.controllers;

import com.cg.stm.models.TaskDto;
import com.cg.stm.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(taskDto));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> findById(@PathVariable Long taskId) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findById(taskId));
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.update(taskId, taskDto));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/multiple")
    public ResponseEntity<List<TaskDto>> createMultipleTasks(@RequestBody List<TaskDto> taskDtoList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createMultipleTasks(taskDtoList));
    }
}
