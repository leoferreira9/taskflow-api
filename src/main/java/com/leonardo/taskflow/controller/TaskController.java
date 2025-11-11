package com.leonardo.taskflow.controller;

import com.leonardo.taskflow.dto.TaskDTO;
import com.leonardo.taskflow.model.Task;
import com.leonardo.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public TaskDTO create(@RequestBody @Valid Task task){
        Task entity = taskService.create(task);
        return new TaskDTO(entity);
    }
}
