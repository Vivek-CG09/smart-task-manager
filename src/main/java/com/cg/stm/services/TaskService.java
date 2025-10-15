package com.cg.stm.services;

import com.cg.stm.models.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto create(TaskDto taskDto);

    TaskDto findById(Long taskId);

    List<TaskDto> findAll();

    TaskDto update(Long taskId, TaskDto taskDto);

    void delete(Long taskId);

    List<TaskDto> createMultipleTasks(List<TaskDto> taskDtoList);
}
