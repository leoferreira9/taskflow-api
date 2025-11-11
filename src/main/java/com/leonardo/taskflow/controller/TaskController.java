package com.leonardo.taskflow.controller;

import com.leonardo.taskflow.dto.TaskDTO;
import com.leonardo.taskflow.model.Task;
import com.leonardo.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<TaskDTO> findAll(){
        List<Task> tasks = taskService.findAll();
        return tasks.stream().map(TaskDTO::new).toList();
    }

    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable Long id, @RequestBody @Valid Task task){
        task.setId(id);
        Task entity = taskService.update(task);
        return new TaskDTO(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        taskService.delete(id);
    }
}
