package com.cg.stm.mappers;

import com.cg.stm.entities.Task;
import com.cg.stm.models.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "status", target = "status")
    TaskDto taskToTaskDto(Task task);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "status", target = "status")
    Task taskDtoToTask(TaskDto taskDto);
}
