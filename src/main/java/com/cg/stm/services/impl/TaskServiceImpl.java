package com.cg.stm.services.impl;

import com.cg.stm.models.TaskDto;
import com.cg.stm.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public TaskDto create(TaskDto taskDto) {
        return null;
    }

    @Override
    public List<TaskDto> findAll() {
        return List.of();
    }

    @Override
    public TaskDto update(TaskDto taskDto) {
        return null;
    }

    @Override
    public void delete(Long taskId) {

    }
}
