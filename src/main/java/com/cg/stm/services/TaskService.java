package com.cg.stm.services;

import com.cg.stm.models.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto create(TaskDto taskDto);

    List<TaskDto> findAll();

    TaskDto update(TaskDto taskDto);

    void delete(Long taskId);
}
