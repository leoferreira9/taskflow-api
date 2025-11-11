package com.leonardo.taskflow.controller;

import com.leonardo.taskflow.dto.TaskDTO;
import com.leonardo.taskflow.model.Task;
import com.leonardo.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public TaskDTO findById(@PathVariable Long id){
        Task task = taskService.findById(id);
        return new TaskDTO(task);
    }
}
