package com.cg.stm.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;

    @NotBlank(message = "TaskName Must not be blank")
    @Size(max = 50, message = "TaskName must not exceed 50 characters")
    private String name;

    @NotBlank(message = "TaskStatus Must not be blank")
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
